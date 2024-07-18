package assets.hello_assets.Controller;

import assets.hello_assets.DTO.AccountAllDTO;
import assets.hello_assets.DTO.CustomerAllDTO;
import assets.hello_assets.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<String> adminHome(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allList")
    public ResponseEntity<ArrayList<CustomerAllDTO>> showAllList(){
        return adminService.AllList();
    }

    @GetMapping("/allAccount")
    public ResponseEntity<ArrayList<AccountAllDTO>> showAllAccount(){
        return adminService.AllAccount();
    }

    @DeleteMapping("/delete/user/{customerName}")
    public ResponseEntity<String> deleteUser(@PathVariable("customerName") String customerName){
        return adminService.deleteUser(customerName);
    }

    @DeleteMapping("/delete/account/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") int accountNumber){
        return adminService.deleteAccount(accountNumber);
    }


}

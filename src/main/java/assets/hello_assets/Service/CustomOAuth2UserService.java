package assets.hello_assets.Service;

import assets.hello_assets.DTO.*;
import assets.hello_assets.Repository.CustomerRepository;
import assets.hello_assets.domain.Customer;
import assets.hello_assets.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final CustomerRepository customerRepository;

    public CustomOAuth2UserService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException { //OAuth2UserRequest  이놈이 리소스 서버에서 제공되는 유저 정보
        OAuth2User oAuth2User = super.loadUser(userRequest); //부모 메서드 호출
        System.out.println("11111");
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();//이 서비스가 네이버인지 구글인지 확인하기 위한 아이디
        System.out.println("22222");
        System.out.println(registrationId);
        OAuth2Response oAuth2Response=null;

        if (registrationId.equals("naver")) {
            oAuth2Response=new NaverResponse(oAuth2User.getAttributes()); //OAuth2User 는 OAuth2UserService 가 소셜에서 받은 여러 가지 정보를 담고 있게 되며, getAttributes() 메서드를 통해 소셜로부터 받은 모든 정보를 추출할 수 있습니다.

            //OAuth2UserRequest 에는 내부적으로 AccessToken 을 가지고 있습니다.

        } else if (registrationId.equals("google")) {

            oAuth2Response=new GoogleResponse(oAuth2User.getAttributes());


        } else {
            return null;
        }

        String customerName=oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        System.out.println(customerName);

        Customer customerData=customerRepository.findByCustomerName(customerName);



        if(customerData == null){
            Customer customer=new Customer();
            customer.setCustomerName(customerName);
            customer.setName(oAuth2Response.getName());
            customer.setEmail(oAuth2Response.getEmail());
            customer.setRole(Role.USER);

            customerRepository.save(customer);

            UserDTO userDTO=new UserDTO();


            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(Role.USER.toString());
            userDTO.setUsername(customerName);

            return new CustomOAuth2User(userDTO);


        }else{

            customerData.setEmail(oAuth2Response.getEmail());
            customerData.setName(oAuth2Response.getName());

            customerRepository.save(customerData);

            UserDTO userDTO=new UserDTO();

            userDTO.setName(customerData.getName());
            userDTO.setRole(Role.USER.toString());
            userDTO.setUsername(customerData.getCustomerName());

            return new CustomOAuth2User(userDTO);


        }


    }
}

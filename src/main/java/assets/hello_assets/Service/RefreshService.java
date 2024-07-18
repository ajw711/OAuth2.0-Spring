package assets.hello_assets.Service;

import assets.hello_assets.Repository.RefreshRepository;
import assets.hello_assets.domain.Refresh;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshRepository refreshRepository;


    public void saveRefresh(String username, String refreshToken , Integer expiration){
        Refresh refresh=new Refresh();
        refresh.setUserName(username);
        refresh.setRefresh(refreshToken);
        refresh.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L).toString());

        refreshRepository.save(refresh);


    }




}

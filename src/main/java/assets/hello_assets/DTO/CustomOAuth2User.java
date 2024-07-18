package assets.hello_assets.DTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuth2User(UserDTO userDTO){
        this.userDTO=userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    } //사용안하는 이유는 구글 네이버 등 방식이 달라서

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //role 값을 리턴하는 메서드

        Collection<GrantedAuthority> collection=new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    } //이름 값을 리턴하는 메서드

    public String getUsername(){
        return userDTO.getUsername();
    } //아이디 값을 리턴하는 메서드
}

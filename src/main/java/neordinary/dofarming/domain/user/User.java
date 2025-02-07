package neordinary.dofarming.domain.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import neordinary.dofarming.common.BaseEntity;
import neordinary.dofarming.domain.user.enums.Role;
import neordinary.dofarming.domain.user.enums.SocialType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Tag(name = "User", description = "유저 정보")
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 20)
    private String username;

    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, name = "social_type")
    private SocialType socialType;


    @Column(length = 20)
    private String nickname;


    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;


    private Boolean isFinished = false; // 추가 정보 입력 완료 여부
    private Boolean isEvaluated = false; //평가 완료 여부

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setIsEvaluated(Boolean isEvaluated) {
        this.isEvaluated = isEvaluated;
    }
}

package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.config.security.userdetails.AccountDetails;
import me.ofnullable.sharebook.file.service.FileStorageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final PasswordEncoder passwordEncoder;
    private final AccountFindService accountFindService;
    private final FileStorageService fileStorageService;

    @Transactional
    public Account update(UpdateAccountRequest dto) {
        var account = accountFindService.findById(dto.getId());
        account.update(dto, passwordEncoder);
        return refreshSecurityContext(account);
    }

    @Transactional
    public Account updateAvatar(Long accountId, MultipartFile avatar) throws IOException {
        var avatarUri = fileStorageService.store(avatar);
        var account = accountFindService.findById(accountId);
        account.updateAvatar(avatarUri);
        return refreshSecurityContext(account);
    }

    private Account refreshSecurityContext(Account account) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return ((AccountDetails) auth.getPrincipal()).refresh(account);
    }

}

package me.ofnullable.sharebook.account.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.file.service.StorageService;
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
    private final StorageService storageService;

    @Transactional
    public Account update(UpdateAccountRequest dto) {
        var account = accountFindService.findById(dto.getId());
        return account.update(dto, passwordEncoder);
    }

    @Transactional
    public Account updateAvatar(Long accountId, MultipartFile avatar) throws IOException {
        var avatarUri = storageService.store(avatar);
        var account = accountFindService.findById(accountId);
        return account.updateAvatar(avatarUri);
    }

}

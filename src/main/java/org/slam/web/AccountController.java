package org.slam.web;

//@Controller
//@AllArgsConstructor
public class AccountController {

//    private final AccountSaveService accountSaveService;

//    @GetMapping("/sign-in")
//    public String signInPage(HttpServletRequest req, Authentication auth) {
//        return this.setPrevPage(req, auth, "sign-in");
//    }
//
//    @GetMapping("/sign-up")
//    public String signUpPage(HttpServletRequest req, Authentication auth) {
//        return this.setPrevPage(req, auth, "sign-up");
//    }
//
//    @PostMapping("/sign-up")
//    public String createAccount(Account account, RedirectAttributes rttr) {
//        accountSaveService.save(account);
//        rttr.addFlashAttribute("createResult", "success");
//        return "redirect:/sign-in";
//    }
//
//    private String setPrevPage(HttpServletRequest req, Authentication auth, String toGo) {
//        String prev = req.getHeader("Referer");
//        if (auth != null) {
//            return prev != null ? "redirect:" + prev : "redirect:/";
//        }
//        if (prev != null && !prev.contains("/sign-in") && !prev.contains("/sign-up")) {
//            req.getSession().setAttribute("prev", prev);
//        }
//        return toGo;
//    }

}
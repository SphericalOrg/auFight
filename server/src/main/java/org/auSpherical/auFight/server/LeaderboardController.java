package org.auSpherical.auFight.server;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

        private final PunctuationService punctuationService;

        public LeaderboardController(PunctuationService punctuationService) {
            this.punctuationService = punctuationService;
        }

        @GetMapping("/")
        public List<Punctuation> getPunctuation() {
            return punctuationService.getPunctuation();
        }

        @GetMapping("/hola")
        public String hola() {
            punctuationService.reset();
            return "Hola";
        }

        @PostMapping("/add")
        public void addPunctuation(@RequestBody Punctuation punctuation) {
            punctuationService.addPunctuation(punctuation);
        }


}

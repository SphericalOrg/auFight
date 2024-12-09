package org.spherical.auserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunctuationService {

    private final PunctuationRepo punctuationRepo;


    public PunctuationService(PunctuationRepo punctuationRepo) {
        this.punctuationRepo = punctuationRepo;
    }

    public List<Punctuation> getPunctuation() {
        return punctuationRepo.findAll();
    }

    public void addPunctuation(Punctuation punctuation) {
        punctuationRepo.save(punctuation);
    }

    public void reset() {
        punctuationRepo.deleteAll();
    }

}

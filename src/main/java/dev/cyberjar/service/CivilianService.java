package dev.cyberjar.service;

import dev.cyberjar.entity.Civilian;
import dev.cyberjar.repository.civilian.CivilianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CivilianService {

    private final CivilianRepository civilianRepository;

    public CivilianService(CivilianRepository civilianRepository) {
        this.civilianRepository = civilianRepository;
    }

    public Civilian getCivilianById(Long id) {
        return civilianRepository.findById(id).orElseThrow();
    }

    public Civilian getCivilianByNationalId(String nationalId) {
        return civilianRepository.findByNationalId(nationalId).orElseThrow();
    }

    public List<Civilian> getCiviliansByLotNumber(int lotNumber) {
        return civilianRepository.findAllByImplantLotNumber(lotNumber);
    }

    public List<Civilian> getCiviliansByLotNumberGreaterOrEqual(int lotNumber) {
        return civilianRepository.findAllByImplantLotNumberGreaterThanEqual(lotNumber);
    }

    public List<Civilian> getCiviliansByLotNumberLessOrEqual(int lotNumber) {
        return civilianRepository.findAllByImplantLotNumberLessThanEqual(lotNumber);
    }


}




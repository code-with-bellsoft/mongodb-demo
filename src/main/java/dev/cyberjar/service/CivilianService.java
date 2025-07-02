package dev.cyberjar.service;

import dev.cyberjar.entity.Civilian;
import dev.cyberjar.entity.Implant;
import dev.cyberjar.repository.civilian.CivilianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CivilianService {

    private final CivilianRepository civilianRepository;

    public CivilianService(CivilianRepository civilianRepository) {
        this.civilianRepository = civilianRepository;
    }

    public Civilian saveCivilian(Civilian civilian) {
        return civilianRepository.save(civilian);

        /*
        can also do
        return civilianRepository.insert(civilian);
         */
    }

    public Civilian updateCivilian(String id, Implant implant) {
        Civilian civilian = civilianRepository.findById(id).orElseThrow();
        civilian.getImplants().add(implant);

        return civilianRepository.save(civilian);

    }

    public Civilian getCivilianById(String id) {
        return civilianRepository.findById(id).orElseThrow();
    }

    public Civilian getCivilianByNationalId(String nationalId) {
        return civilianRepository.findByNationalId(nationalId).orElseThrow();
    }

    public List<Civilian> getAllCivilians() {
        return civilianRepository.findAll();
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

    public void deleteCivilian(Civilian civilian) {
        civilianRepository.delete(civilian);
    }

    public void deleteAllCivilians() {
        civilianRepository.deleteAll();
    }


}




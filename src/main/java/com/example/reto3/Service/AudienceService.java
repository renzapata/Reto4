package com.example.reto3.Service;

import com.example.reto3.Repository.AudienceRepository;
import com.example.reto3.model.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceService {

    @Autowired
    private AudienceRepository audienceRepository;

    public List<Audience> getAll() {
        return audienceRepository.getAll ();
    }

    public Optional<Audience> getAudience(int id){
        return audienceRepository.getAudience(id);
    }

    public Audience save(Audience audiences){
        if (audiences.getId() == null){
            return audienceRepository.save(audiences);
             }else {
            Optional<Audience> auditorio1 = audienceRepository.getAudience(audiences.getId());
            if(auditorio1.isEmpty()){
                return audienceRepository.save(audiences);
            }else{
                return audiences;
            }
        }
    }
    public Audience update(Audience audience){
        if(audience.getId()!=null){
            Optional<Audience> e= audienceRepository.getAudience(audience.getId());
            if(!e.isEmpty()){
                if(audience.getName()!=null){
                    e.get().setName(audience.getName());
                }
                if(audience.getOwner()!=null){
                    e.get().setOwner(audience.getOwner());
                }
                if(audience.getCapacity()!=null){
                    e.get().setCapacity(audience.getCapacity());
                }
                if(audience.getDescription()!=null){
                    e.get().setDescription(audience.getDescription());
                }
                if(audience.getCategory()!=null){
                    e.get().setCategory(audience.getCategory());
                }
                audienceRepository.save(e.get());
                return e.get();
            }else{
                return audience;
            }
        }else{
            return audience;
        }
    }


    public boolean deleteAudience(int id){
        Boolean d = getAudience(id).map(audience -> {
            audienceRepository.delete(audience);
            return true;
        }).orElse(false);
        return d;
    }

}

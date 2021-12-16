package omds.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import omds.Intake;
import omds.data.IntakeRepository;

@Slf4j
@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignIntakeController {
    private IntakeRepository intakeRepo;

    @Autowired
    EntityLinks entityLinks;
    
    @Autowired
    public DesignIntakeController(IntakeRepository intakeRepo){
        this.intakeRepo = intakeRepo;
    }

    @GetMapping("/recent")
    public CollectionModel<EntityModel<Intake>> recentIntakes(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdat").descending());
        List<Intake> intakes =  intakeRepo.findAll(page).getContent();
        CollectionModel<EntityModel<Intake>> recentCollectionModel = CollectionModel.wrap(intakes);
        recentCollectionModel.add(
            WebMvcLinkBuilder.linkTo(DesignIntakeController.class)
                             .slash("recent")
                             //.recentIntakes())
                             .withRel("recents")
        );
        return recentCollectionModel;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intake> intakeById(@PathVariable("id") Long id){
        Optional<Intake> optIntake = intakeRepo.findById(id);
        if(optIntake.isPresent()){
            return new ResponseEntity<>(optIntake.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Intake postIntake(@RequestBody Intake intake){
        return intakeRepo.save(intake);
    }

    
} 

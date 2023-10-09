package springboot.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    CountryRepository countryRespository;

    @GetMapping
    public Iterable<Country> getAllCountries() {
        return countryRespository.findAll();
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return new ResponseEntity<Country>(countryRespository.save(country), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable String id) {
        int countryId = Integer.parseInt(id);
        Optional<Country> countryOptional = countryRespository.findById(countryId);
        if (countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping
    // public List<Country> search(@RequestBody Map<String, String> body){
    // String searchTerm = body.get("text");
    // return
    // countryRespository.findByTitleContainingOrContentContaining(searchTerm,
    // searchTerm);
    // }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCountry(@PathVariable String id, @RequestBody Country countryDetails) {
        int countryId = Integer.parseInt(id);
        Optional<Country> countryOptional = countryRespository.findById(countryId);
        if (countryOptional.isPresent()) {
            Country updatedCountry = countryOptional.get();
            updatedCountry.setCountry(countryDetails.getCountry());
            updatedCountry.setPopulation(countryDetails.getPopulation());
            countryRespository.save(updatedCountry);
            // return ResponseEntity.ok(updatedCountry);
            return ResponseEntity.noContent().build();
            // ResponseEntity<Void>
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCountry(@PathVariable String id) {
        int countryId = Integer.parseInt(id);
        Optional<Country> countryOptional = countryRespository.findById(countryId);
        if (countryOptional.isPresent()) {
            countryRespository.deleteById(countryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}




// @GetMapping
// public List<Employee> getAllEmployees(){
// return employeeRepository.findAll();
// }

// // build create employee REST API
// @PostMapping
// public Employee createEmployee(@RequestBody Employee employee) {
// return employeeRepository.save(employee);
// }

// // build get employee by id REST API
// @GetMapping("{id}")
// public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
// Employee employee = employeeRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with
// id:" + id));
// return ResponseEntity.ok(employee);
// }

// // build update employee REST API
// @PutMapping("{id}")
// public ResponseEntity<Employee> updateEmployee(@PathVariable long
// id,@RequestBody Employee employeeDetails) {
// Employee updateEmployee = employeeRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
// " + id));

// updateEmployee.setFirstName(employeeDetails.getFirstName());
// updateEmployee.setLastName(employeeDetails.getLastName());
// updateEmployee.setEmailId(employeeDetails.getEmailId());

// employeeRepository.save(updateEmployee);

// return ResponseEntity.ok(updateEmployee);
// }

// // build delete employee REST API
// @DeleteMapping("{id}")
// public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

// Employee employee = employeeRepository.findById(id)
// .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
// " + id));

// employeeRepository.delete(employee);

// return new ResponseEntity<>(HttpStatus.NO_CONTENT);

// }
// }




// package example.cashcard;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
// import java.net.URI;

// import org.springframework.web.util.UriComponentsBuilder;



// @RestController
// @RequestMapping("/cashcards")
// public class CashCardController {

// private CashCardRepository cashCardRepository;

// // dependency injection (DI) framework, specifically constructor injection,
// // to supply CashCardController with the correct implementation of
// CashCardRepository at runtime


// public CashCardController(CashCardRepository cashCardRepository) {
// this.cashCardRepository = cashCardRepository;
// }

// private Optional<CashCard> findCashCard(Long requestedId) {
// return cashCardRepository.findById(requestedId);
// }

// // We're calling CrudRepository.findById which returns an Optional.
// // If cashCardOptional.isPresent() is true then the repository successfully
// found the CashCard
// // and we can retrieve it with cashCardOptional.get().
// @GetMapping("/{requestedId}")
// public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
// Optional<CashCard> cashCardOptional = findCashCard(requestedId);
// if (cashCardOptional.isPresent()) {
// return ResponseEntity.ok(cashCardOptional.get());
// } else {
// return ResponseEntity.notFound().build();
// }
// }

// // uses the @PostMapping annotation from Spring Web
// //we must provide a Location header with the URI for where to find the newly
// created CashCard
// // deserialize the data submitted to API into a CashCard
// @PostMapping
// private ResponseEntity<Void> createCashCard(@RequestBody CashCard
// newCashCardRequest, UriComponentsBuilder ucb) {
// CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
// // saves a new CashCard for us, and returns the saved object with a unique id
// provided by the database
// URI locationOfNewCashCard = ucb
// .path("cashcards/{id}")
// .buildAndExpand(savedCashCard.id())
// .toUri();
// // URI that the caller can then use to GET the newly-created CashCard and
// UriComponentsBuilder is ucb
// return ResponseEntity.created(locationOfNewCashCard).build(); // 201 status
// and location header in response
// }

// @GetMapping
// public ResponseEntity<List<CashCard>> findAll(Pageable pageable) {
// Page<CashCard> page = cashCardRepository.findAll(
// PageRequest.of(
// pageable.getPageNumber(),
// pageable.getPageSize(),
// pageable.getSortOr(Sort.by(Sort.Direction.DESC, "amount"))));
// // pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
// return ResponseEntity.ok(page.getContent());
// }

// @PutMapping("/{requestedId}")
// private ResponseEntity<Void> putCashCard(@PathVariable Long requestedId,
// @RequestBody CashCard cashCardUpdate) {
// Optional<CashCard> cashCardOptional = findCashCard(requestedId);
// if (cashCardOptional.isPresent()) {
// CashCard updatedCashCard = new CashCard(requestedId,
// cashCardUpdate.amount());
// cashCardRepository.save(updatedCashCard);
// // just return 204 NO CONTENT for now.
// return ResponseEntity.noContent().build();
// }
// return ResponseEntity.notFound().build();
// }

// @DeleteMapping("/{id}")
// private ResponseEntity<Void> deleteCashCard(@PathVariable Long id) {
// Optional<CashCard> cashCardOptional = findCashCard(id);
// if (cashCardOptional.isPresent()) {
// cashCardRepository.deleteById(id);
// return ResponseEntity.noContent().build();
// }
// return ResponseEntity.notFound().build();
// }

// }
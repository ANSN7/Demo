package springboot.crud;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    //JpaRepository

    // custom query to search to blog post by title or content
    // List<Country> findByTitleContainingOrContentContaining(String text, String textAgain);
}



// public interface EmployeeRepository extends JpaRepository<Employee, Long> {
// // all crud database methods
// }

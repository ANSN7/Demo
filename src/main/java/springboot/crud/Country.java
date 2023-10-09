package springboot.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "country_name")
    private String country;

    @Column(name = "population")
    private int population;
}



// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class Country {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int id;

//     private String country;
//     private int population;

//     public Country(String country, int population) {
//         this.setCountry(country);
//         this.setPopulation(population);
//     }

//     public Country(int id, String country, int population) {
//         this.setId(id);
//         this.setCountry(country);
//         this.setPopulation(population);
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getCountry() {
//         return country;
//     }

//     public void setCountry(String country) {
//         this.country = country;
//     }

//     public int getPopulation() {
//         return population;
//     }

//     public void setPopulation(int population) {
//         this.population = population;
//     }

//     // @Override
//     // public String toString() {
//     //     return "Country{" +
//     //             "id=" + id +
//     //             ", title='" + title + ''' +
//     //             ", content='" + content + ''' +
//     //             '}';
//     // }
// }
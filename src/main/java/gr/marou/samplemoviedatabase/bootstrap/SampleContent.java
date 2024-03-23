package gr.marou.samplemoviedatabase.bootstrap;

import com.thedeanda.lorem.LoremIpsum;
import gr.marou.samplemoviedatabase.dto.BestActorNominationDTO;
import gr.marou.samplemoviedatabase.dto.MovieDTO;
import gr.marou.samplemoviedatabase.dto.PersonDTO;
import gr.marou.samplemoviedatabase.dto.TVShowDTO;
import gr.marou.samplemoviedatabase.mapper.BestActorNominationMapper;
import gr.marou.samplemoviedatabase.mapper.MovieMapper;
import gr.marou.samplemoviedatabase.mapper.PersonMapper;
import gr.marou.samplemoviedatabase.mapper.TVShowMapper;
import gr.marou.samplemoviedatabase.model.BestActorNomination;
import gr.marou.samplemoviedatabase.model.Movie;
import gr.marou.samplemoviedatabase.model.Person;
import gr.marou.samplemoviedatabase.model.TVShow;
import gr.marou.samplemoviedatabase.model.enums.Genres;
import gr.marou.samplemoviedatabase.model.enums.Professions;
import gr.marou.samplemoviedatabase.model.enums.Result;
import gr.marou.samplemoviedatabase.model.enums.SalaryType;
import gr.marou.samplemoviedatabase.service.BestActorNominationService;
import gr.marou.samplemoviedatabase.service.MovieService;
import gr.marou.samplemoviedatabase.service.PersonService;
import gr.marou.samplemoviedatabase.service.TVShowService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SampleContent implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SampleContent.class);

    private static final int NUM_ACTORS = 50;
    private static final int NUM_DIRECTORS = 4;
    private static final int NUM_PRODUCERS = 2;
    private static final int NUM_CREW_MEMBERS = 10;
    private static final int NUM_MOVIES = 5;
    private static final int NUM_TV_SHOWS = 5;
    private static final int NUM_BEST_ACTOR_NOMINATIONS = 40;

    private final PersonService personService;
    private final MovieService movieService;
    private final TVShowService tvShowService;
    private final BestActorNominationService bestActorNominationService;
    private final PersonMapper personMapper;
    private final MovieMapper movieMapper;
    private final TVShowMapper tvShowMapper;
    private final BestActorNominationMapper bestActorNominationMapper;

    @Override
    public void run(String... args) throws Exception {

        LOG.info("Generating sample content...");
        List<Person> actors = generateActors(NUM_ACTORS);
        List<Person> directors = generateDirectors(NUM_DIRECTORS);
        List<Person> producers = generateProducers(NUM_PRODUCERS);
        List<Person> crewMembers = generateCrewMembers(NUM_CREW_MEMBERS);
        List<Movie> movies = generateMovies(NUM_MOVIES,actors,directors,producers);
        List<TVShow> tvShows = generateTVShows(NUM_TV_SHOWS, actors, directors, producers);
        List<BestActorNomination> bestActorNominations = generateBestActorNominations(NUM_BEST_ACTOR_NOMINATIONS, actors);
        LOG.info("Sample content generation completed.");
    }

    /**
        Data for Actors
     */
    private List<Person> generateActors(int count) {
        List<Person> actors = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Person actor = Person.builder()
                    .firstName(LoremIpsum.getInstance().getFirstName())
                    .lastName(LoremIpsum.getInstance().getLastName())
                    .professions(Professions.ACTOR)
                    .salary(BigDecimal.valueOf(1500 + Math.random() * 2500))
                    .salaryType(Math.random() < 0.5 ? SalaryType.FULL_PROJECT : SalaryType.PER_EPISODE)
                    .build();
            actors.add(actor);
        }
        List<PersonDTO> actorDTOs = actors.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
        personService.createAll(actorDTOs);
        LOG.info("Generated {} actors", actors.size());
        return actors;
    }

    /**
        Data for Directors
     */
    private List<Person> generateDirectors(int count) {
        List<Person> directors = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Person director = Person.builder()
                    .firstName(LoremIpsum.getInstance().getFirstName())
                    .lastName(LoremIpsum.getInstance().getLastName())
                    .professions(Professions.DIRECTOR)
                    .salary(BigDecimal.valueOf(2500 + Math.random() * 4000))
                    .salaryType(Math.random() < 0.5 ? SalaryType.FULL_PROJECT : SalaryType.PER_EPISODE)
                    .build();
            directors.add(director);
        }
        List<PersonDTO> directorDTOs = directors.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
        personService.createAll(directorDTOs);
        LOG.info("Generated {} directors", directors.size());
        return directors;
    }

    /**
        Data for Producers
     */
    private List<Person> generateProducers(int count) {
        List<Person> producers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Person producer = Person.builder()
                    .firstName(LoremIpsum.getInstance().getFirstName())
                    .lastName(LoremIpsum.getInstance().getLastName())
                    .professions(Professions.PRODUCER)
                    .salary(BigDecimal.valueOf(5000 + Math.random() * 7000))
                    .salaryType(Math.random() < 0.5 ? SalaryType.FULL_PROJECT : SalaryType.PER_EPISODE)
                    .build();
            producers.add(producer);
        }

        List<PersonDTO> producerDTOs = producers.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
        personService.createAll(producerDTOs);
        LOG.info("Generated {} producers", producers.size());
        return producers;
    }

    /**
        Data for Crew Members
     */
    private List<Person> generateCrewMembers(int count) {
        List<Person> crewMembers = new ArrayList<>();
        // Data for Crew Members
        for (int i = 1; i <= count; i++) {
            Person crewmember = Person.builder()
                    .firstName(LoremIpsum.getInstance().getFirstName())
                    .lastName(LoremIpsum.getInstance().getLastName())
                    .professions(Professions.PRODUCER)
                    .salary(BigDecimal.valueOf(1000 + Math.random() * 2000))
                    .salaryType(Math.random() < 0.5 ? SalaryType.FULL_PROJECT : SalaryType.PER_EPISODE)
                    .build();
            crewMembers.add(crewmember);
        }

        List<PersonDTO> crewmemberDTOs = crewMembers.stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
        personService.createAll(crewmemberDTOs);
        LOG.info("Generated {} crew members", crewMembers.size());
        return  crewMembers;
    }

    /**
        Data for Movies
     */
    private List<Movie> generateMovies(int count, List<Person> actors, List<Person> directors, List<Person> producers) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Movie movie = Movie.builder()
                    .title(LoremIpsum.getInstance().getWords(5))
                    .productionBudget(new BigDecimal((i + 1) * 100000))
                    .releaseYear(LoremIpsum.getInstance().getPriorDate(Duration.ofDays(365)).getYear())
                    .genre(Genres.values()[i % Genres.values().length])
                    .actors((i % 2 == 0) ? actors.subList(0, 10) : actors.subList(10, 20))
                    .director(directors.get(new Random().nextInt(directors.size())))
                    .producers(Collections.singletonList(i % 2 == 0 ? producers.get(0) : producers.get(1)))
                    .build();
            movies.add(movie);
        }
        List<MovieDTO> movieDTOs = movies.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
        movieService.createAll(movieDTOs);
        LOG.info("Generated {} movies", movies.size());
        return movies;
    }

    /**
        Data for TvShows
     */
    private List<TVShow> generateTVShows(int count, List<Person> actors, List<Person> directors, List<Person> producers) {
        List<TVShow> tvShows = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            TVShow tvShow = TVShow.builder()
                    .title(LoremIpsum.getInstance().getWords(5))
                    .genre(Genres.values()[(i + 2) % Genres.values().length])
                    .startYear(LoremIpsum.getInstance().getPriorDate(Duration.ofDays(2000 + i)).getYear())
                    .endYear(LoremIpsum.getInstance().getPriorDate(Duration.ofDays(2005 + i)).getYear())
                    .numberOfEpisodes(12 + i)
                    .minProductionBudgetPerEpisode(new BigDecimal((i + 1) * 20))
                    .maxProductionBudgetPerEpisode(new BigDecimal((i + 2) * 100))
                    .actors((i % 2 == 0) ? actors.subList(0, 10) : actors.subList(10, 20))
                    .director(directors.get(new Random().nextInt(directors.size())))
                    .producers(Collections.singletonList(i % 2 == 0 ? producers.get(0) : producers.get(1)))
                    .build();
            tvShows.add(tvShow);
        }
        List<TVShowDTO> tvShowDTOs = tvShows.stream()
                .map(tvShowMapper::toDto)
                .collect(Collectors.toList());
        tvShowService.createAll(tvShowDTOs);
        LOG.info("Generated {} TV shows", tvShows.size());
        return tvShows;
    }

    /**
        Data for Nominations
     */
    private List<BestActorNomination> generateBestActorNominations(int count, List<Person> actors) {
        List<BestActorNomination> bestActorNominations = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            BestActorNomination nomination = BestActorNomination.builder()
                    .actor(actors.get(0))
                    .genre(Genres.values()[(i + 1) % Genres.values().length])
                    .nominationYear(LoremIpsum.getInstance().getPriorDate(Duration.ofDays((long) (1774 + Math.random() * 700))).getYear())
                    .result(Result.NOMINATED)
                    .build();
            bestActorNominations.add(nomination);
        }
        List<BestActorNominationDTO> bestActorNominationsDtos = bestActorNominations.stream()
                .map(bestActorNominationMapper::toDto)
                .collect(Collectors.toList());
        bestActorNominationService.createAll(bestActorNominationsDtos);
        LOG.info("Generated {} best actor nominations", bestActorNominations.size());
        return bestActorNominations;
    }

}

package interviews.java;

import java.util.*;

class Person {
    String name;
    List<Person> friends;
    List<String> likedMovies;

    public Person(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
    }

    public void addFriend(Person friend) {
    	//friend.addFriend(this);
        friends.add(friend);
    }

    public void addLikedMovie(String movie) {
        likedMovies.add(movie);
    }

    public List<Person> getFriends() {
        return friends;
    }

    public List<String> getLikedMovies() {
        return likedMovies;
    }
}

public class MovieNetwork {

    public static String mostPopularMovie(Person person) {
        Map<String, Integer> movieCount = new HashMap<>();
        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();

        queue.add(person);
        visited.add(person);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            for (String movie : current.getLikedMovies()) {
                movieCount.put(movie, movieCount.getOrDefault(movie, 0) + 1);
            }
            for (Person friend : current.getFriends()) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    queue.add(friend);
                }
            }
        }

        String mostPopularMovie = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : movieCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopularMovie = entry.getKey();
            }
        }

        return mostPopularMovie;
    }

    public static void main(String[] args) {
        Person aarti = new Person("Aarti");
        Person brijith = new Person("Brijith");
        Person chad = new Person("Chad");

        aarti.addFriend(brijith);
        brijith.addFriend(chad);     //
        chad.addFriend(brijith);
        chad.addFriend(aarti);
        
        aarti.addLikedMovie("DevD");
        
        brijith.addLikedMovie("DevD");
        brijith.addLikedMovie("Barbie");
        
        chad.addLikedMovie("DevD");
        chad.addLikedMovie("Barbie");
        chad.addLikedMovie("King Charlie");

        System.out.println("Most popular movie in Alice's network: " + mostPopularMovie(brijith));
        //OUTPUT : Most popular movie in Alice's network: DevD
    }
}

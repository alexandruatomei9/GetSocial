# GetSocial

This project is focused on gathering data regarding a user profile on one or multiple social networks. 
The information that we will gather will contain details regarding the users' preferences about books, movies, music and/or other hobbies.
Based on the collected data we will "draw" a user profile which we will later use to match individuals from the same social network.

##Keywords

Social Network, User Profiling, Cloud Computing, Stable Matching Algorithms,  Local Algorithms, Stable States

##Ideea 

Psychologists claim that you have a limit of how many friends you can handle so we thought to try improving the way a user gets his friendship suggestions on a social networks so he could improve his "happiness" and "commodity" in the on-line environment. The main idea is that a user will recieve friendship suggestions based on the hobbies listed on his profile and not on the already existing set of friends. This could be a wrong idea but it is worth a shot.

##Related Work
1. Stable-matching
An early ancestor of our work is the stable marriage problem, introduced by Gale and Shapley in 1962: We are given n nodes, partitioned into two sets commonly denoted as men and women. Each woman has a strictly ordered preference list over all men and vice versa. They now want to create a stable matching. A matching is called stable if there is no pair of man and woman such that, instead of being matched to their current partner, they would prefer to be matched to each other.
2. User profiling



##Model

Starting from the central idea of the Gale-Shapley algorithm and given an user(let's call him Luke) from a Social Network we will denote two sets of users for the individual in cause: 
  1. one will contain the actual friends of Luke
  2. the other will contain the users that are not YET friends with Luke.(We will select the users that are not friends with the individual in cause based on an algorithm described below).

The algorithm that we will be using to select the users that are not friends with Luke follows the next steps:
  1. Find all the users that are friends with Luke at a moment
  2. For every friend get a list of its friends(removing duplicates so we avoid circularity)
  3. Repeat Step 2 one more time for every individual in the current list

We will create a profile for every user in these two sets and based on the maximum number of friends that Luke can have we will try to make him happy by suggesting the most similar users to become his friends.

When developing our application we will use different APIs like: Facebook DataApi, TwitterApi, IMDB API, ArtistLink, goodreads and others in order to creat a user's profile that should look like this:
```json
{
    "userId": "123",
    "userName": "user.name",
    "books":
    [
        {
          "type": "drama", 
          "titles": ["drama 1", "drama 2"]
        },
        {
          "type": "comedy",
          "titles": ["comedy 1", "comedy 2", "comedy 3"]
        }
    ],
    "music":
    [
       ... 
    ]
    ...
}
```

We will use the profiles created to find the N best friends for Luke(where N is the maximum number of friends that Luke can have) by calculating a score for every user based on the common "features" between them and Luke(for example they both like comedy moovies and they have watched at least same 10 movies).


##Useful Links

[A partner-matching framework for social activity communities](http://www.computationalsocialnetworks.com/content/pdf/s40649-014-0005-0.pdf )

[User profile matching in social networks](https://hal.archives-ouvertes.fr/file/index/docid/643509/filename/User_Profile_Matching_in_Social_Networks.pdf )

[On Finding Better Friends in Social Networks](http://www.tik.ee.ethz.ch/file/d4748aa2644eca9eb6aa143029e9c2d1/Social.pdf)

[Local Matching Dynamics in Social Networks](https://people.mpi-inf.mpg.de/~mhoefer/08-0x/Hoefer11Matching.pdf)

[Facebook API - get friends](https://developers.facebook.com/docs/graph-api/reference/v2.5/user/friends)

[Facebook API - get books](https://developers.facebook.com/docs/graph-api/reference/user/books/)

[Facebook API - get movies](https://developers.facebook.com/docs/graph-api/reference/user/movies/)

[Facebook API - get music](https://developers.facebook.com/docs/graph-api/reference/user/music/)

[Facebook API - get TV shows](https://developers.facebook.com/docs/graph-api/reference/user/television/)

[Twitter API - get friend suggestions](https://dev.twitter.com/rest/reference/get/users/suggestions)

[Stable Matching Algorithms Articles](https://scholar.google.ro/scholar?q=stable+matching+algorithms+and+social+networks&hl=ro&as_sdt=0&as_vis=1&oi=scholart&sa=X&ved=0CBwQgQMwAGoVChMIlPfs04HgyAIVC9IaCh3E2gls)

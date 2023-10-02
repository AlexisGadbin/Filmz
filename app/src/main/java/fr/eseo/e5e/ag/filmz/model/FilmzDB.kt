package fr.eseo.e5e.ag.filmz.model

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.eseo.e5e.ag.filmz.model.daos.ArtistDao
import fr.eseo.e5e.ag.filmz.model.daos.CountryDao
import fr.eseo.e5e.ag.filmz.model.daos.GenreDao
import fr.eseo.e5e.ag.filmz.model.daos.MovieDao
import fr.eseo.e5e.ag.filmz.model.daos.RoleDao
import fr.eseo.e5e.ag.filmz.model.entities.Artist
import fr.eseo.e5e.ag.filmz.model.entities.Country
import fr.eseo.e5e.ag.filmz.model.entities.Genre
import fr.eseo.e5e.ag.filmz.model.entities.Movie
import fr.eseo.e5e.ag.filmz.model.entities.Role
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Artist::class, Country::class, Genre::class, Movie::class, Role::class],
    version = 4,
    exportSchema = false)
abstract class FilmzDB : RoomDatabase() {
  abstract fun artists(): ArtistDao

  abstract fun countries(): CountryDao

  abstract fun genres(): GenreDao

  abstract fun movies(): MovieDao

  abstract fun roles(): RoleDao

  companion object {
    @Volatile private var INSTANCE: FilmzDB? = null

    fun instance(context: Context, scope: CoroutineScope): FilmzDB {
      Log.d("DB", "called instance")
      synchronized(this) {
        var instance = INSTANCE
        if (instance == null) {
          Log.d("DB", "creating instance")
          instance =
              Room.databaseBuilder(
                      context.applicationContext, FilmzDB::class.java, "Filmz Database")
                  .fallbackToDestructiveMigration()
                  .addCallback(FilmzDBCallback(scope))
                  .build()
          INSTANCE = instance
        }

        return instance
      }
    }

    class FilmzDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.d("DB", "onCreate() Called")
        INSTANCE?.let { database ->
          scope.launch(Dispatchers.IO) {
            populateDatabase(
                database.movies(),
                database.countries(),
                database.genres(),
                database.roles(),
                database.artists())
          }
        }
      }

      suspend fun populateDatabase(
          movies: MovieDao,
          countries: CountryDao,
          genres: GenreDao,
          roles: RoleDao,
          artists: ArtistDao
      ) {

        Log.d("ROOM", "populate Countries")
        var country = Country("AL", "Albania", "Albanian")
        countries.insert(country)
        country = Country("DZ", "Algeria", "Algerian")
        countries.insert(country)
        country = Country("AR", "Argentina", "Argentine Argentinian")
        countries.insert(country)
        country = Country("AU", "Australia", "Australian")
        countries.insert(country)
        country = Country("AT", "Austria", "Austrian")
        countries.insert(country)
        country = Country("BD", "Bangladesh", "Bangladeshi")
        countries.insert(country)
        country = Country("BE", "Belgium", "Belgian")
        countries.insert(country)
        country = Country("BO", "Bolivia", "Bolivian")
        countries.insert(country)
        country = Country("BW", "Botswana", "Batswana")
        countries.insert(country)
        country = Country("BR", "Brazil", "Brazilian")
        countries.insert(country)
        country = Country("BG", "Bulgaria", "Bulgarian")
        countries.insert(country)
        country = Country("KH", "Cambodia", "Cambodian")
        countries.insert(country)
        country = Country("CM", "Cameroon", "Cameroonian")
        countries.insert(country)
        country = Country("CA", "Canada", "Canadian")
        countries.insert(country)
        country = Country("CL", "Chile", "Chilean")
        countries.insert(country)
        country = Country("CN", "China", "Chinese")
        countries.insert(country)
        country = Country("CO", "Colombia", "Colombian")
        countries.insert(country)
        country = Country("CR", "Costa Rica", "Costa Rican")
        countries.insert(country)
        country = Country("HR", "Croatia", "Croatian")
        countries.insert(country)
        country = Country("CU", "Cuba", "Cuban")
        countries.insert(country)
        country = Country("CZ", "Czech Republic", "Czech")
        countries.insert(country)
        country = Country("DK", "Denmark", "Danish")
        countries.insert(country)
        country = Country("DO", "Dominican Republic", "Dominican")
        countries.insert(country)
        country = Country("EC", "Ecuador", "Ecuadorian")
        countries.insert(country)
        country = Country("EG", "Egypt", "Egyptian")
        countries.insert(country)
        country = Country("SV", "El Salvador", "Salvadorian")
        countries.insert(country)
        country = Country("EE", "Estonia", "Estonian")
        countries.insert(country)
        country = Country("ET", "Ethiopia", "Ethiopian")
        countries.insert(country)
        country = Country("FJ", "Fiji", "Fijian")
        countries.insert(country)
        country = Country("FI", "Finland", "Finnish")
        countries.insert(country)
        country = Country("FR", "France", "French")
        countries.insert(country)
        country = Country("DE", "Germany", "German")
        countries.insert(country)
        country = Country("GH", "Ghana", "Ghanaian")
        countries.insert(country)
        country = Country("GR", "Greece", "Greek")
        countries.insert(country)
        country = Country("GT", "Guatemala", "Guatemalan")
        countries.insert(country)
        country = Country("HT", "Haiti", "Haitian")
        countries.insert(country)
        country = Country("HN", "Honduras", "Honduran")
        countries.insert(country)
        country = Country("HU", "Hungary", "Hungarian")
        countries.insert(country)
        country = Country("IS", "Iceland", "Icelandic")
        countries.insert(country)
        country = Country("IN", "India", "Indian")
        countries.insert(country)
        country = Country("ID", "Indonesia", "Indonesian")
        countries.insert(country)
        country = Country("IR", "Iran", "Iranian")
        countries.insert(country)
        country = Country("IQ", "Iraq", "Iraqi")
        countries.insert(country)
        country = Country("IE", "Ireland", "Irish")
        countries.insert(country)
        country = Country("IL", "Israel", "Israeli")
        countries.insert(country)
        country = Country("IT", "Italy", "Italian")
        countries.insert(country)
        country = Country("JM", "Jamaica", "Jamaican")
        countries.insert(country)
        country = Country("JP", "Japan", "Japanese")
        countries.insert(country)
        country = Country("JO", "Jordan", "Jordanian")
        countries.insert(country)
        country = Country("KE", "Kenya", "Kenyan")
        countries.insert(country)
        country = Country("KR", "Korea (the Republic of)", "Korean")
        countries.insert(country)
        country = Country("KW", "Kuwait", "Kuwaiti")
        countries.insert(country)
        country = Country("LA", "Laos", "Lao")
        countries.insert(country)
        country = Country("LV", "Latvia", "Latvian")
        countries.insert(country)
        country = Country("LB", "Lebanon", "Lebanese")
        countries.insert(country)
        country = Country("LY", "Libya", "Libyan")
        countries.insert(country)
        country = Country("LT", "Lithuania", "Lithuanian")
        countries.insert(country)
        country = Country("MG", "Madagascar", "Malagasy")
        countries.insert(country)
        country = Country("MY", "Malaysia", "Malaysian")
        countries.insert(country)
        country = Country("ML", "Mali", "Malian")
        countries.insert(country)
        country = Country("MT", "Malta", "Maltese")
        countries.insert(country)
        country = Country("MX", "Mexico", "Mexican")
        countries.insert(country)
        country = Country("MN", "Mongolia", "Mongolian")
        countries.insert(country)
        country = Country("MA", "Morocco", "Moroccan")
        countries.insert(country)
        country = Country("MZ", "Mozambique", "Mozambican")
        countries.insert(country)
        country = Country("NA", "Namibia", "Namibian")
        countries.insert(country)
        country = Country("NP", "Nepal", "Nepalese")
        countries.insert(country)
        country = Country("NL", "Netherlands", "Dutch")
        countries.insert(country)
        country = Country("NZ", "New Zealand", "New Zealand")
        countries.insert(country)
        country = Country("NI", "Nicaragua", "Nicaraguan")
        countries.insert(country)
        country = Country("NG", "Nigeria", "Nigerian")
        countries.insert(country)
        country = Country("NO", "Norway", "Norwegian")
        countries.insert(country)
        country = Country("PK", "Pakistan", "Pakistani")
        countries.insert(country)
        country = Country("PA", "Panama", "Panamanian")
        countries.insert(country)
        country = Country("PY", "Paraguay", "Paraguayan")
        countries.insert(country)
        country = Country("PE", "Peru", "Peruvian")
        countries.insert(country)
        country = Country("PH", "Philippines", "Philippine")
        countries.insert(country)
        country = Country("PL", "Poland", "Polish")
        countries.insert(country)
        country = Country("PT", "Portugal", "Portuguese")
        countries.insert(country)
        country = Country("RO", "Romania", "Romanian")
        countries.insert(country)
        country = Country("RU", "Russia", "Russian")
        countries.insert(country)
        country = Country("SA", "Saudi Arabia", "Saudi")
        countries.insert(country)
        country = Country("SN", "Senegal", "Senegalese")
        countries.insert(country)
        country = Country("RS", "Serbia", "Serbian")
        countries.insert(country)
        country = Country("SG", "Singapore", "Singaporean")
        countries.insert(country)
        country = Country("SK", "Slovakia", "Slovak")
        countries.insert(country)
        country = Country("ZA", "South Africa", "South African")
        countries.insert(country)
        country = Country("ES", "Spain", "Spanish")
        countries.insert(country)
        country = Country("LK", "Sri Lanka", "Sri Lankan")
        countries.insert(country)
        country = Country("SD", "Sudan", "Sudanese")
        countries.insert(country)
        country = Country("SE", "Sweden", "Swedish")
        countries.insert(country)
        country = Country("CH", "Switzerland", "Swiss")
        countries.insert(country)
        country = Country("SY", "Syria", "Syrian")
        countries.insert(country)
        country = Country("TW", "Taiwan", "Taiwanese")
        countries.insert(country)
        country = Country("TJ", "Tajikistan", "Tajikistani")
        countries.insert(country)
        country = Country("TH", "Thailand", "Thai")
        countries.insert(country)
        country = Country("TO", "Tonga", "Tongan")
        countries.insert(country)
        country = Country("TN", "Tunisia", "Tunisian")
        countries.insert(country)
        country = Country("TR", "Turkey", "Turkish")
        countries.insert(country)
        country = Country("UA", "Ukraine", "Ukrainian")
        countries.insert(country)
        country = Country("AE", "United Arab Emirates", "Emirati")
        countries.insert(country)
        country = Country("GB", "United Kingdom", "British")
        countries.insert(country)
        country = Country("US", "United States of America", "American")
        countries.insert(country)
        country = Country("UY", "Uruguay", "Uruguayan")
        countries.insert(country)
        country = Country("VE", "Venezuela", "Venezuelan")
        countries.insert(country)
        country = Country("VN", "Vietnam", "Vietnamese")
        countries.insert(country)
        country = Country("ZM", "Zambia", "Zambian")
        countries.insert(country)
        country = Country("ZW", "Zimbabwe", "Zimbabwean")
        countries.insert(country)
        Log.d("ROOM", "Countries finished")
        Log.d("ROOM", "populate Genres")
        var genre = Genre(1, "drama", "Drama")
        genres.insert(genre)
        genre = Genre(2, "sci-fi", "Science Fiction")
        genres.insert(genre)
        genre = Genre(3, "comedy", "Comedy")
        genres.insert(genre)
        genre = Genre(4, "romcom", "Romantic Comedy")
        genres.insert(genre)
        genre = Genre(5, "period", "Period Drama")
        genres.insert(genre)
        genre = Genre(6, "action", "Action")
        genres.insert(genre)
        genre = Genre(7, "horror", "Horror")
        genres.insert(genre)
        genre = Genre(8, "fantasy", "Fantasy")
        genres.insert(genre)
        genre = Genre(9, "police", "Police / Crime")
        genres.insert(genre)
        genre = Genre(10, "western", "Cowboy and Indians")
        genres.insert(genre)
        Log.d("ROOM", "Genres finished")
        Log.d("ROOM", "populate Artists")
        var artist = Artist(1, "Hitchcock", "Alfred", 1899, "GB")
        artists.insert(artist)
        artist = Artist(2, "Stewart", "James", 1908, "US")
        artists.insert(artist)
        artist = Artist(3, "Novak", "Kim", 1933, "US")
        artists.insert(artist)
        artist = Artist(4, "Gilliam", "Terry", 1940, "US")
        artists.insert(artist)
        artist = Artist(5, "Chapman", "Graham", 1940, "GB")
        artists.insert(artist)
        artist = Artist(6, "Cleese", "John", 1940, "GB")
        artists.insert(artist)
        artist = Artist(7, "Idle", "Eric", 1940, "GB")
        artists.insert(artist)
        artist = Artist(8, "Jones", "Terry", 1940, "GB")
        artists.insert(artist)
        artist = Artist(9, "Palin", "Michael", 1940, "GB")
        artists.insert(artist)
        artist = Artist(10, "Booth", "Connie", 1940, "GB")
        artists.insert(artist)
        artist = Artist(11, "Lucas", "George", 1944, "US")
        artists.insert(artist)
        artist = Artist(12, "Ford", "Harrison", 1942, "US")
        artists.insert(artist)
        artist = Artist(13, "Hamill", "Mark", 1951, "US")
        artists.insert(artist)
        artist = Artist(14, "Fisher", "Carrie", 1956, "US")
        artists.insert(artist)
        artist = Artist(15, "Cushing", "Peter", 1913, "GB")
        artists.insert(artist)
        artist = Artist(16, "Mayhew", "Peter", 1944, "GB")
        artists.insert(artist)
        artist = Artist(17, "Arden Oplev", "Niels", 1961, "DK")
        artists.insert(artist)
        artist = Artist(18, "Nyqvist", "Michael", 1960, "SE")
        artists.insert(artist)
        artist = Artist(19, "Rapace", "Noomi", 1979, "SE")
        artists.insert(artist)
        artist = Artist(20, "Badham", "John", 1939, "US")
        artists.insert(artist)
        artist = Artist(21, "Broderick", "Matthew", 1962, "US")
        artists.insert(artist)
        artist = Artist(22, "Coleman", "Dabney", 1932, "US")
        artists.insert(artist)
        artist = Artist(23, "Softley", "Iain", 1956, "GB")
        artists.insert(artist)
        artist = Artist(24, "Miller", "Jonny Lee", 1972, "GB")
        artists.insert(artist)
        artist = Artist(25, "Jolie", "Angelina", 1975, "US")
        artists.insert(artist)
        artist = Artist(26, "Sena", "Dominic", 1949, "US")
        artists.insert(artist)
        artist = Artist(27, "Travolta", "John", 1954, "US")
        artists.insert(artist)
        artist = Artist(28, "Jackman", "Hugh", 1968, "AU")
        artists.insert(artist)
        artist = Artist(29, "Berry", "Halle", 1966, "US")
        artists.insert(artist)
        artist = Artist(30, "Cheadle", "Don", 1964, "US")
        artists.insert(artist)
        artist = Artist(31, "Loncraine", "Richard", 1946, "GB")
        artists.insert(artist)
        artist = Artist(32, "Madson", "Virginia", 1961, "US")
        artists.insert(artist)
        artist = Artist(33, "Soderbergh", "Steven", 1961, "US")
        artists.insert(artist)
        artist = Artist(34, "Clooney", "George", 1961, "US")
        artists.insert(artist)
        artist = Artist(35, "Pitt", "Brad", 1961, "US")
        artists.insert(artist)
        artist = Artist(36, "Mann", "Michael", 1943, "US")
        artists.insert(artist)
        artist = Artist(37, "Hemsworth", "Chris", 1983, "AU")
        artists.insert(artist)
        artist = Artist(38, "Wei", "Tang", 1979, "CN")
        artists.insert(artist)
        artist = Artist(39, "Davis", "Viola", 1965, "US")
        artists.insert(artist)
        Log.d("ROOM", "Artists finished")
        Log.d("ROOM", "populate Movies")
        var movie =
            Movie(
                1,
                "Vertigo",
                1958,
                1,
                1,
                "Alfred Hitchcock`s superb thriller about a detective whose fear of heights leaves him unable to stop a woman`s suicide. Later he spots a girl who looks just like the dead woman, reigniting his obsession with the mystery.",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "1")
        movie =
            Movie(
                2,
                "Monty Python and the Holy Grail",
                1975,
                4,
                3,
                "History is turned on its comic head when, in tenth century England, King Arthur travels the countryside to find knights who will join him at the Round Table in Camelot. Gathering up the men is a tale in itself but after a bit of a party at Camelot, many decide to leave only to be stopped by God, who sends them on a quest: to find the Holy Grail. After a series of individual adventures, the knights are reunited but must face a wizard named Tim the Enchanter, killer rabbits and lessons in the use of holy hand grenades.",
                "GB")
        movies.insert(movie)
        Log.d("ROOM", "2")
        movie =
            Movie(
                3,
                "Star Wars : A New Hope",
                1977,
                11,
                2,
                "The Imperial Forces, under orders from cruel Darth Vader, hold Princess Leia hostage in their efforts to quell the rebellion against the Galactic Empire. Luke Skywalker and Han Solo, captain of the Millennium Falcon, work together with the companionable droid duo R2-D2 and C-3PO to rescue the beautiful princess, help the Rebel Alliance and restore freedom and justice to the Galaxy.",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "3")
        movie =
            Movie(
                4,
                "The Girl with the Dragon Tattoo",
                2009,
                17,
                9,
                "Forty years ago, Harriet Vanger disappeared from a family gathering on the island owned and inhabited by the powerful Vanger clan. Her body was never found, yet her uncle suspects murder and that the killer is a member of his own tightly knit but dysfunctional family. He employs disgraced financial journalist Mikael Blomkvist and the tattooed, ruthless computer hacker Lisbeth Salander to investigate. When the pair link Harriet`s disappearance to a number of grotesque murders from almost forty years ago, they begin to unravel a dark and appalling family history; but, the Vangers are a secretive clan, and Blomkvist and Salander are about to find out just how far they are prepared to go to protect themselves.",
                "SE")
        movies.insert(movie)
        Log.d("ROOM", "4")
        movie =
            Movie(
                5,
                "WarGames",
                1983,
                20,
                1,
                "A young computer whiz kid accidentally connects into a top secret super-computer which has complete control over the U.S. nuclear arsenal. It challenges him to a game between America and Russia, and he innocently starts the countdown to World War 3. Can he convince the computer he wanted to play a game and not the real thing ?",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "5")
        movie =
            Movie(
                6,
                "Hackers",
                1995,
                23,
                1,
                "Dade Murphy was a hacker even as a kid in Seattle. He got arrested for the computer virus that he planted and was banned from using any computer until the age of 18.Then he moves to New York to meet a group of hackers. He also falls in love with Kate Libby.",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "6")
        movie =
            Movie(
                7,
                "Swordfish",
                2001,
                26,
                6,
                "When the DEA shut down its dummy corporation operation codenamed SWORDFISH in 1986, they had generated $400 million which they let sit around; fifteen years of compound interest has swelled it to $9.5 billion. A covert counter-terrorist unit called Black Cell, headed by the duplicitious and suave Gabriel Shear, wants the money to help finance their raise-the-stakes vengeance war against international terrorism, but it`s all locked away behind super-encryption. He brings in convicted hacker Stanley Jobson, who only wants to see his daughter Holly again but can`t afford the legal fees, to slice into the government mainframes and get the money.",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "7")
        movie =
            Movie(
                8,
                "Firewall",
                2006,
                31,
                6,
                "As a bid to pay off his family`s ransom a security specialist named Jack Stanfield is forced into robbing a bank that he is protecting! His family gets kidnapped and taken to the middle of nowhere and it`s up to Jack Stanfield to rescue his family and get the money back!",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "8")
        movie =
            Movie(
                9,
                "Ocean`s Thirteen",
                2007,
                33,
                9,
                "The last time we saw Danny Ocean`s crew, they were paying back ruthless casino mogul Terry Benedict after stealing millions from him. However, it`s been a while since they`ve come back together, which is all about to change. When one of their own, Reuben Tishkoff, builds a hotel with another casino owner, Willy Bank, the last thing he ever wanted was to get cut out of the deal personally by the loathsome Bank. Bank`s attitude even goes so far as to finding the amusement in Tishkoff`s misfortune when the double crossing lands Reuben in the hospital because of a heart attack. However, Danny and his crew won`t stand for Bank and what he`s done to a friend. Uniting with their old enemy Benedict, who himself has a vendetta against Bank, the crew is out to pull off a major plan; one that will unfold on the night Bank`s newest hot spot opens up. They`re not in this for the money, but for the revenge",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "9")
        movie =
            Movie(
                10,
                "Blackhat",
                2015,
                36,
                6,
                "Nick Hathaway, an extremely talented hacker who has gone astray, finds his way out of a 15 year prison sentence when parts of a computer code he once wrote during his youth appears in a malware that triggered a terrorist attack in a nuclear power plant in China. This opportunity will reunite him with an old friend but will also put him in the middle of a power game between the American and Chinese government as well as an arch villain hacker whose identity he has to find if he wants to keep his freedom and his life.",
                "US")
        movies.insert(movie)
        Log.d("ROOM", "10")
        Log.d("ROOM", "Movies finished")
        Log.d("ROOM", "populate Roles")
        var role = Role(1, 2, "John (Scottie) Ferguson")
        roles.insert(role)
        role = Role(1, 3, "Judy Barton")
        roles.insert(role)
        role = Role(1, 3, "Madeleine Elster")
        roles.insert(role)
        role = Role(2, 4, "Patsy")
        roles.insert(role)
        role = Role(2, 5, "Arthur, King of the Britons")
        roles.insert(role)
        role = Role(2, 6, "Sir Lancelot the Brave")
        roles.insert(role)
        role = Role(2, 7, "Sir Robin the Not-Quite-So-Brave-as-Sir-Lancelot")
        roles.insert(role)
        role = Role(2, 8, "Sir Bedevere the Wise")
        roles.insert(role)
        role = Role(2, 9, "Sir Galahad the Pure")
        roles.insert(role)
        role = Role(2, 10, "Miss Islington")
        roles.insert(role)
        role = Role(3, 12, "Han Solo")
        roles.insert(role)
        role = Role(3, 13, "Luke Skywalker")
        roles.insert(role)
        role = Role(3, 14, "Princess Leia Organa")
        roles.insert(role)
        role = Role(3, 15, "GRand Moff Tarkin")
        roles.insert(role)
        role = Role(3, 16, "Chewbacca")
        roles.insert(role)
        role = Role(4, 18, "Mikael Blomkvist")
        roles.insert(role)
        role = Role(4, 19, "Lisbeth Salander")
        roles.insert(role)
        role = Role(5, 21, "David Lightman")
        roles.insert(role)
        role = Role(5, 22, "Dr John McKittrick")
        roles.insert(role)
        role = Role(6, 24, "Dade Murphy")
        roles.insert(role)
        role = Role(7, 26, "Kate Libby")
        roles.insert(role)
        role = Role(7, 27, "Gabriel Shear")
        roles.insert(role)
        role = Role(7, 28, "Stanley Jobson")
        roles.insert(role)
        role = Role(7, 29, "Ginger Knowles")
        roles.insert(role)
        role = Role(7, 30, "Agent J.T. Roberts")
        roles.insert(role)
        role = Role(8, 12, "Jack Stanfield")
        roles.insert(role)
        role = Role(8, 32, "Beth Stanfield")
        roles.insert(role)
        role = Role(9, 34, "Danny Ocean")
        roles.insert(role)
        role = Role(9, 35, "Rusty Ryan")
        roles.insert(role)
        role = Role(9, 30, "Basher Tarr")
        roles.insert(role)
        role = Role(10, 37, "Nicholas Hathaway")
        roles.insert(role)
        role = Role(10, 38, "Chen Lien")
        roles.insert(role)
        role = Role(10, 39, "Carol Barrett")
        roles.insert(role)
        Log.d("ROOM", "Roles finished")
      }
    }
  }
}

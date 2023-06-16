create database team9;
use team9;

create table Actor
(
    ActorID      int          not null
        primary key,
    Name         varchar(255) null,
    Nationality  varchar(255) null,
    NotableWorks varchar(255) null,
    ImagePath    varchar(255) null,
    biography    text         null
);

create table Director
(
    DirectorID   int          not null
        primary key,
    Name         varchar(255) null,
    Nationality  varchar(255) null,
    NotableWorks varchar(255) null,
    ImagePath    varchar(255) null,
    biography    text         null
);

create table Movie
(
    MovieID     int          not null
        primary key,
    Title       varchar(255) null,
    DirectorID  int          null,
    ReleaseDate date         null,
    Duration    int          null,
    RateAvg     int          null,
    RateCount   int          null,
    PicUrl      varchar(255) null,
    Overview    varchar(500) null,
    BannerPath  varchar(255) null,
    constraint movie_ibfk_1
        foreign key (DirectorID) references Director (DirectorID)
            on update cascade on delete set null
);

create index DirectorID
    on Movie (DirectorID);

create table MovieActor
(
    MovieID int not null,
    ActorID int not null,
    constraint `PRIMARY`
        primary key (MovieID, ActorID),
    constraint movieactor_ibfk_1
        foreign key (MovieID) references Movie (MovieID)
            on update cascade on delete cascade,
    constraint movieactor_ibfk_2
        foreign key (ActorID) references Actor (ActorID)
            on update cascade on delete cascade
);

create table MovieGenre
(
    GenreID   int          not null
        primary key,
    GenreName varchar(255) null
);

create table MovieAndGenre
(
    MovieID int not null,
    GenreID int not null,
    constraint `PRIMARY`
        primary key (MovieID, GenreID),
    constraint movieandgenre_ibfk_1
        foreign key (MovieID) references Movie (MovieID),
    constraint movieandgenre_ibfk_2
        foreign key (GenreID) references MovieGenre (GenreID)
            on update cascade on delete cascade
);

create table User
(
    UserID     varchar(20)  not null
        primary key,
    Username   varchar(255) null,
    Password   varchar(255) null,
    Email      varchar(255) null,
    AvatarPath varchar(255) null
);

create table Review
(
    ReviewID   int auto_increment
        primary key,
    MovieId    int           null,
    Rating     decimal(3, 1) null,
    Content    text          null,
    UserID     varchar(20)   null,
    ReviewTime datetime      null,
    constraint review_ibfk_1
        foreign key (UserID) references User (UserID),
    constraint review_ibfk_2
        foreign key (MovieId) references Movie (MovieID)
);

create index MovieId
    on Review (MovieId);

create index UserID
    on Review (UserID);

create table UserMovieFavorite
(
    UserID  varchar(20) not null,
    MovieID int         not null,
        primary key (UserID, MovieID),
    constraint usermoviefavorite_ibfk_1
        foreign key (UserID) references User (UserID),
    constraint usermoviefavorite_ibfk_2
        foreign key (MovieID) references Movie (MovieID)
);

create index MovieID
    on UserMovieFavorite (MovieID);
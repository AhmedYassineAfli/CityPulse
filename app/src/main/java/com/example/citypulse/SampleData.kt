package com.example.citypulse

/**
 * Jeu de données de démonstration de 18 événements (Atelier 7 & 8 & 10).
 */
fun sampleEvents(): List<Event> = listOf(
    // Concerts
    Event(
        id = 1,
        title = "Concert acoustique au parc",
        category = "Concert",
        date = "Sam. 4 oct. · 19h00",
        location = "Parc de la Médina",
        imageUrl = "",
        description = "Une soirée intimiste sous les arbres avec des artistes locaux revisitant des classiques en acoustique. Venez avec vos proches profiter d'une ambiance douce et chaleureuse."
    ),
    Event(
        id = 2,
        title = "Nuit du jazz",
        category = "Concert",
        date = "Ven. 10 oct. · 20h30",
        location = "Théâtre municipal",
        imageUrl = "",
        description = "Une célébration des grands standards du jazz avec des ensembles internationaux et des solistes renommés. Deux heures d'improvisations et de rythmes envoûtants."
    ),
    Event(
        id = 3,
        title = "Festival rock indépendant",
        category = "Concert",
        date = "Sam. 18 oct. · 18h00",
        location = "Complexe culturel",
        imageUrl = "",
        description = "Scène ouverte aux talents émergents du rock alternatif et indie. Énergie pure, riffs puissants et découvertes musicales au rendez-vous."
    ),
    Event(
        id = 4,
        title = "Soirée chants traditionnels",
        category = "Concert",
        date = "Jeu. 23 oct. · 19h30",
        location = "Maison de la Culture",
        imageUrl = "",
        description = "Plongez dans le patrimoine musical ancestral avec des voix exceptionnelles accompagnées d'instruments traditionnels en bois et en cordes."
    ),
    Event(
        id = 5,
        title = "Concert de musique électronique",
        category = "Concert",
        date = "Ven. 31 oct. · 22h00",
        location = "Club Underground",
        imageUrl = "",
        description = "Performances live synthétiseurs, beats percutants et jeu de lumières immersif orchestré par des DJs producteurs renommés."
    ),
    Event(
        id = 6,
        title = "Festival de la chanson tunisienne",
        category = "Concert",
        date = "Sam. 8 nov. · 19h00",
        location = "Théâtre municipal",
        imageUrl = "",
        description = "Un hommage vibrant aux mélodies et textes qui ont marqué l'histoire de la musique tunisienne, interprétés par un orchestre symphonique."
    ),

    // Expos
    Event(
        id = 7,
        title = "Exposition photo urbaine",
        category = "Expo",
        date = "Dim. 5 oct. · 10h–18h",
        location = "Galerie El Kantara",
        imageUrl = "",
        description = "Regards croisés de photographes contemporains sur l'architecture, les contrastes de lumière et la vie quotidienne dans nos métropoles."
    ),
    Event(
        id = 8,
        title = "Rétrospective peinture moderne",
        category = "Expo",
        date = "Mer. 15 oct. · 9h–17h",
        location = "Galerie El Kantara",
        imageUrl = "",
        description = "Un demi-siècle de mouvements artistiques modernes à travers une sélection exclusive de toiles majeures et d'œuvres sur papier."
    ),
    Event(
        id = 9,
        title = "Expo artisanat local",
        category = "Expo",
        date = "Sam. 25 oct. · 10h–19h",
        location = "Place du Gouvernorat",
        imageUrl = "",
        description = "Découvrez le savoir-faire des artisans de la région : poteries, tissages, maroquinerie d'art et créations durables présentées par leurs auteurs."
    ),
    Event(
        id = 10,
        title = "Installation art numérique",
        category = "Expo",
        date = "Mar. 28 oct. · 11h–20h",
        location = "Centre d'art contemporain",
        imageUrl = "",
        description = "Une expérience sensorielle immersive à base de projections 3D interactives, de capteurs sonores et d'art génératif en temps réel."
    ),
    Event(
        id = 11,
        title = "Exposition sculpture contemporaine",
        category = "Expo",
        date = "Sam. 1 nov. · 10h–18h",
        location = "Galerie El Kantara",
        imageUrl = "",
        description = "Des volumes audacieux en métal, marbre et bois recyclé explorant les tensions de l'équilibre et les formes géométriques de notre époque."
    ),

    // Sports
    Event(
        id = 12,
        title = "Tournoi de basket 3x3",
        category = "Sport",
        date = "Mer. 8 oct. · 17h30",
        location = "Terrain municipal",
        imageUrl = "",
        description = "Compétition de basket urbain en format officiel 3x3. Équipes mixtes, ambiance DJ et animations street culture tout au long de l'après-midi."
    ),
    Event(
        id = 13,
        title = "Course solidaire 10 km",
        category = "Sport",
        date = "Dim. 12 oct. · 8h00",
        location = "Stade Olympique",
        imageUrl = "",
        description = "Courir pour la bonne cause sur un parcours boisé sécurisé. Ouvert à tous les niveaux, ravitaillements et médailles finisher garantis."
    ),
    Event(
        id = 14,
        title = "Match de football amical",
        category = "Sport",
        date = "Sam. 18 oct. · 16h00",
        location = "Stade Olympique",
        imageUrl = "",
        description = "Rencontre sportive de gala entre sélections régionales. Venez soutenir les joueurs dans une ambiance festive et familiale."
    ),
    Event(
        id = 15,
        title = "Tournoi de volley-ball",
        category = "Sport",
        date = "Dim. 19 oct. · 14h00",
        location = "Complexe sportif",
        imageUrl = "",
        description = "Phase finale inter-quartiers avec 12 équipes en lice pour remporter le trophée de la ville. Smashs, défenses héroïques et spectacle."
    ),
    Event(
        id = 16,
        title = "Initiation à l'escalade",
        category = "Sport",
        date = "Mer. 22 oct. · 17h00",
        location = "Salle d'escalade",
        imageUrl = "",
        description = "Séance découverte encadrée par des moniteurs diplômés. Matériel fourni pour apprendre les bases de l'assurage et de la grimpe en toute sécurité."
    ),
    Event(
        id = 17,
        title = "Marathon de la ville",
        category = "Sport",
        date = "Dim. 26 oct. · 7h30",
        location = "Centre-ville",
        imageUrl = "",
        description = "L'épreuve reine de 42,195 km traversant les plus beaux monuments et avenues de la ville. Venez encourager les milliers de marathoniens."
    ),
    Event(
        id = 18,
        title = "Tournoi d'échecs",
        category = "Sport",
        date = "Dim. 2 nov. · 9h00",
        location = "Maison de la Culture",
        imageUrl = "",
        description = "Tournoi homologué en 7 rondes système suisse, cadence 15min + 10s. Ouvert à toutes les catégories d'âge et niveaux elo."
    )
)

package com.example.utrechtontdekker.data

import com.example.utrechtontdekker.R
import com.example.utrechtontdekker.model.Location
import com.example.utrechtontdekker.model.LocationType
import com.example.utrechtontdekker.model.LocationType.*

object DataSource {
    val locations: List<Location> = listOf(
        // Coffee shops
        Location(
            tag = "coffee_de_ontdekking",
            displayName = "De Ontdekking",
            type = COFFEE_SHOP,
            descriptionHtml = """
                <p><i>Dichtbij het centraal station.</i><br>
                <br>
                <strong>Toelichting:</strong>Een ideale start- of eindplek van uw reis. Ze staan bekend om hun goede koffie en snelle, vriendelijke service.<br>
                <br>
                <strong>Tip:</strong> Perfect voor de vroege vogels, omdat ze al vroeg open zijn voor een snelle *coffee-to-go* voordat de trein vertrekt.</p>
                """.trimIndent()
        ),
        Location(
            tag = "coffee_the_village",
            displayName = "The Village Coffee & Music",
            type = COFFEE_SHOP,
            descriptionHtml = """
                |<p><strong>Toelichting:</strong> Een hippe, industriële hotspot waar koffie en muziek samenkomen. De sfeer is relaxed en de koffie van hoge kwaliteit.<br>
                |<br>
                |<strong>Tip:</strong> Blijf even hangen voor hun zorgvuldig geselecteerde vinylcollectie en vraag het personeel om hun favoriete album.</p>
                |""".trimMargin()
        ),
        Location(
            tag = "coffee_klaas_broodje_co",
            displayName = "Klaas, Broodje & Co",
            type = COFFEE_SHOP,
            descriptionHtml = """
                <p><strong>Toelichting:</strong> Meer dan alleen koffie; ze serveren ook heerlijke, verse broodjes. Een perfecte plek voor een complete lunch.<br>
                <br>
                <strong>Tip:</strong> Probeer een van hun rijk belegde lunchbroodjes. De ingrediënten zijn lokaal en vers.</p>
                """.trimIndent()
        ),
        Location(
            tag = "coffee_blackbird",
            displayName = "Blackbird Coffee & Vintage",
            type = COFFEE_SHOP,
            descriptionHtml = """
                <p><strong>Toelichting:</strong> Een unieke combinatie van een koffiebar en een vintage winkel. Geniet van uw espresso terwijl u snuffelt tussen de retrokleding en accessoires.<br>
                <br>
                <strong>Tip:</strong> Kijk goed rond in de winkel! U vindt hier vaak verborgen pareltjes en unieke cadeaus.</p>
                """.trimIndent()
        ),

        // Restaurants
        Location(
            tag = "restaurant_wt_urban_kitchen",
            displayName = "WT Urban Kitchen",
            type = RESTAURANT,
            descriptionHtml = """<p><i>Industrial atmosphere, view</i><br><br><strong>Toelichting:</strong>Gelegen in een oude watertoren, biedt dit restaurant een spectaculair uitzicht over de stad. De sfeer is chique industrieel en de keuken is verfijnd.<br><br><strong>Tip:</strong> Reserveer een tafel bij het raam om tijdens het diner van de zonsondergang over Utrecht te genieten.</p>"""
        ),
        Location(
            tag = "restaurant_de_zakkendrager",
            displayName = "De Zakkendrager (Tapas & drinks)",
            type = RESTAURANT,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een gezellige en informele plek aan de Oude Gracht, ideaal voor een avond vol kleine, smaakvolle tapasgerechten en een goed glas wijn.<br><br><strong>Tip:</strong> Deel verschillende gerechten met uw gezelschap en vergeet de huisgemaakte sangria niet te proberen.</p>"""
        ),
        Location(
            tag = "restaurant_bistro_le_clochard",
            displayName = "Bistro Le Clochard (French cuisine)",
            type = RESTAURANT,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een klassieke Franse bistro met een warme, intieme inrichting. Hier eet u traditionele Franse gerechten van hoge kwaliteit.<br><br><strong>Tip:</strong> Dit is de perfecte plek voor een romantisch diner. Vraag naar de dagspecialiteiten; ze zijn vaak uitstekend.</p>"""
        ),
        Location(
            tag = "restaurant_loetje",
            displayName = "Loetje (Steak & classics)",
            type = RESTAURANT,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een begrip in Nederland. Beroemd om de malse ossenhaasbiefstuk in jus en de ongedwongen, bruine-kroeg-achtige sfeer.<br><br><strong>Tip:</strong> Bestel de biefstuk *Bali* voor een pittige twist. Vraag ook om extra brood om in de jus te dippen!</p>"""
        ),
        Location(
            tag = "restaurant_broadway_american_steakhouse",
            displayName = "Broadway American Steakhouse",
            type = RESTAURANT,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een Amerikaans geïnspireerd restaurant met een focus op perfect gegrilde steaks en klassieke bijgerechten.<br><br><strong>Tip:</strong> Ze hebben vaak goede aanbiedingen voor de lunch. Een aanrader voor liefhebbers van een perfect gegaarde *ribeye*.</p>"""
        ),

        // Family-friendly places
        Location(
            tag = "family_railway_museum",
            displayName = "Railway Museum (Spoorwegmuseum)",
            type = FAMILY_FRIENDLY,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een interactief museum dat de geschiedenis van de trein tot leven brengt. Het is groot, overzichtelijk en heeft veel te doen voor kinderen van alle leeftijden.<br><br><strong>Tip:</strong> Neem de tijd voor de attractie *De Vuurproef*. Let op: koop de kaartjes online om de wachtrijen te vermijden.</p>"""
        ),
        Location(
            tag = "family_naturalis",
            displayName = "Naturalis Biodiversity Center (Leiden)",
            type = FAMILY_FRIENDLY,
            descriptionHtml = """<p><strong>Toelichting:</strong> Hoewel een stukje reizen (in Leiden), is het een van de grootste en meest spectaculaire natuurhistorische musea van Europa, met als hoogtepunt de T-rex Trix.<br><br><strong>Tip:</strong> Maak er een dagje uit van en combineer het met een wandeling door het historische centrum van Leiden.</p>"""
        ),
        Location(
            tag = "family_griftpark",
            displayName = "Griftpark (Large playground & petting zoo)",
            type = FAMILY_FRIENDLY,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een multifunctioneel park met een van de grootste en populairste speeltuinen van Utrecht. De kinderboerderij is een hit bij de kleintjes.<br><br><strong>Tip:</strong> Neem zelf picknickspullen mee en geniet op het grasveld naast de speeltuin terwijl de kinderen spelen.</p>"""
        ),
        Location(
            tag = "family_pet_farm_klopvaart",
            displayName = "Stadstuin Klopvaart Petting Zoo",
            type = FAMILY_FRIENDLY,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een kleinschalige, gezellige stadsboerderij waar kinderen dicht bij de dieren kunnen komen. Ideaal voor een rustige middag.<br><br><strong>Tip:</strong> Controleer de voedertijden op de website – kinderen vinden het fantastisch om hierbij te helpen.</p>"""
        ),

        // Parks
        Location(
            tag = "park_lepelenburg",
            displayName = "Lepelenburg Park",
            type = PARK,
            descriptionHtml = """<p><strong>Toelichting:</strong> Dit stadspark ligt dicht bij het centrum och de grachten. Het is een populaire plek om te zonnen en voor kleinschalige evenementen.<br><br><strong>Tip:</strong> Op mooie dagen is dit de ideale plek om met een deken neer te strijken en mensen te kijken.</p>"""
        ),
        Location(
            tag = "park_griftpark",
            displayName = "Griftpark",
            type = PARK,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een ruim park met meer dan alleen een kinderboerderij; er is ook een skatebaan en sportveldjes.<br><br><strong>Tip:</strong> Neem sportschoenen mee om een balletje te trappen of te tennissen op de openbare veldjes.</p>"""
        ),
        Location(
            tag = "park_maximapark",
            displayName = "Máximapark",
            type = PARK,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een van de grootste parken van Utrecht, perfect voor lange wandelingen, hardlopen of fietsen. Bevat de Japanse tuin en de Bosspeeltuin.<br><br><strong>Tip:</strong> Huur een fiets om het park in zijn geheel te verkennen. De vlinderhof en de boomgaarden zijn prachtig.</p>"""
        ),
        Location(
            tag = "park_wilhelminapark",
            displayName = "Wilhelminapark",
            type = PARK,
            descriptionHtml = """<p><strong>Toelichting:</strong> Een historisch en statig park in de wijk Oost. Een prachtige plek met een grote vijver en oude bomen.<br><br><strong>Tip:</strong> Breng een bezoek in de herfst wanneer de bladeren verkleuren; het is een fotogenieke plek.</p>"""
        ),

        // Shopping areas
        Location(
            tag = "shopping_hoog_catharijne",
            displayName = "Hoog Catharijne",
            type = SHOPPING_AREA,
            descriptionHtml = """<p><strong>Toelichting:</strong> Het grootste, overdekte winkelcentrum van Utrecht, direct verbonden met het Centraal Station. U vindt hier alle grote (internationale) ketens.<br><br><strong>Tip:</strong> Gebruik dit winkelcentrum als binnendoorroute van het station naar de binnenstad op regenachtige dagen.</p>"""
        ),
        Location(
            tag = "shopping_vredenburg",
            displayName = "Vredenburg Shopping Area",
            type = SHOPPING_AREA,
            descriptionHtml = """<p><strong>Toelichting:</strong> Deels winkelcentrum, deels plein. Het plein is de thuishaven van de wekelijkse markt en de omgeving is gevuld met warenhuizen.<br><br><strong>Tip:</strong> Bezoek de zaterdagse bloemenmarkt voor een explosie van kleur en geur.</p>"""
        ),
        Location(
            tag = "shopping_lange_elisabethstraat_steenweg",
            displayName = "Lange Elisabethstraat / Steenweg",
            type = SHOPPING_AREA,
            descriptionHtml = """<p><strong>Toelichting:</strong> De centrale winkelstraten die het stationsgebied met de binnenstad verbinden. Hier vindt u een mix van grote ketens en kleinere boetieks.<br><br><strong>Tip:</strong> Wijk af naar de zijstraatjes, zoals de Zadelstraat, voor unieke speciaalzaken en onafhankelijke winkels.</p>"""
        )
    )

    val locationTypeImages: Map<LocationType, Int> = mapOf(
        Pair(COFFEE_SHOP, R.drawable.coffee),
        Pair(RESTAURANT, R.drawable.restaurant),
        Pair(FAMILY_FRIENDLY, R.drawable.playground),
        Pair(PARK, R.drawable.cherry_blossom),
        Pair(SHOPPING_AREA, R.drawable.shop),
    )

    fun getLocationsByType(type: LocationType): List<Location> {
        return locations.filter { it.type == type }
    }
}
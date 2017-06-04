package kg.ksucta.dkfai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georg beier on 02.06.2017.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/names")
public class NamesController {
    private List<NameRecord> names = NameRecord.initialise();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String[] getNames() {
        String[] n = new String[names.size()];
        int idx = 0;
        for (NameRecord nameRec : names) {
            n[idx++] = nameRec.name;
        }
        return n;
    }

    @RequestMapping(path = "/{idx}", method = RequestMethod.GET)
    @ResponseBody
    public NameRecord getName(@PathVariable Integer idx) {
        if (idx < 0 || idx >= names.size()) {
            throw new RecordNotFoundException(idx);
        }
        return names.get(idx);
    }
}

class NameRecord {
    private static int count = 0;

    public int id;
    public String name;
    public String imageUrl;
    public String about;
    private int stars = 0;
    public int ratings = 0;

    public NameRecord(String name, String url, String about) {
        id = count++;
        this.name = name;
        this.imageUrl = url;
        this.about = about;
    }

    public float getStars() {
        if (ratings == 0)
            return 0;
        else
            return ((float) stars) / ((float) ratings);
    }

    public static List<NameRecord> initialise() {
        List<NameRecord> names = new ArrayList<>();
        count = 0;
        names.add(new NameRecord("Jodie Foster", "Jodie_Foster.jpg", "Jodie Foster (* 19. November 1962 in Los Angeles, Kalifornien; eigentlich Alicia Christian Foster) ist eine US-amerikanische Schauspielerin, Filmregisseurin, Filmproduzentin und zweifache Oscar-Preisträgerin."));
        names.add(new NameRecord("Woody Allen", "Woody_Allen.jpg", "Woody Allen (* 1. Dezember 1935 als Allan Stewart Konigsberg in Brooklyn, New York), bürgerlich seit 1952 Heywood Allen, ist ein US-amerikanischer Komiker, Filmregisseur, Autor, Schauspieler und Musiker. Neben den über 50 Filmen als Drehbuchautor und Regisseur hat er zahlreiche Erzählungen, Theaterstücke und Kolumnen geschrieben. Darüber hinaus ist er passionierter Jazzmusiker."));
        names.add(new NameRecord("Bonnie", "Bonnie.jpg", "Bonnie und Clyde war ein aus den Kriminellen Bonnie Elizabeth Parker und Clyde Champion Barrow bestehendes US-amerikanisches Verbrecherduo."));
        names.add(new NameRecord("Clyde", "Clyde.jpg", "Bonnie und Clyde war ein aus den Kriminellen Bonnie Elizabeth Parker und Clyde Champion Barrow bestehendes US-amerikanisches Verbrecherduo."));
        names.add(new NameRecord("Queen Mary", "Queen_Mary.jpg", "Die RMS Queen Mary ist ein ehemaliges Passagierschiff, das von 1936 bis 1967 für die Reederei Cunard Line im Einsatz war. Sie liegt seither fest vertäut im kalifornischen Long Beach und wird als schwimmendes Hotel namens Hotel The Queen Mary genutzt."));
        names.add(new NameRecord("Mary Poppins", "Mary_Poppins.jpg", "Mary Poppins ist die Hauptfigur in Romanen der australischen Schriftstellerin P. L. Travers (1899–1996). Die englische Ausgabe erschien erstmals 1934 bei G. Howe in London. Die deutsche Erstausgabe erschien 1952 im Cecilie Dressler Verlag. Die Übersetzung stammte von Elisabeth Kessel."));
        names.add(new NameRecord("Harry Potter", "Harry_Potter.jpg", "Harry Potter ist eine Fantasy-Romanreihe der englischen Schriftstellerin Joanne K. Rowling. Erzählt wird die siebenteilige Geschichte des Titelhelden Harry James Potter, eines Schülers des britischen Zaubererinternats Hogwarts, und seiner Konfrontationen mit dem bösen Magier Lord Voldemort und dessen Gefolgsleuten, den sogenannten Todessern. Jeder der sieben Bände beschreibt ein Schul- und Lebensjahr von Harry Potter, beginnend kurz vor seinem elften Geburtstag."));
        names.add(new NameRecord("Boris Gruschenko", "Boris_Gruschenko.jpg", "Die letzte Nacht des Boris Gruschenko ist ein US-amerikanischer Film aus dem Jahre 1975. Regisseur, Autor und Hauptdarsteller der Komödie ist Woody Allen. Er spielt in dieser Satire einen tollpatschigen Feigling, der durch Zufall zum Kriegshelden wird und später bei dem Versuch scheitert, Napoléon Bonaparte zu ermorden."));
        return names;
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Object id) {
        super("could not find record with '" + id.toString() + "'.");
    }
}

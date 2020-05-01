package com.example.habits.Motivation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.habits.R
import kotlinx.android.synthetic.main.fragment_motivation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime


/**
 * A simple [Fragment] subclass.
 */
class FragmentMotivation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_motivation, container, false)
    super.onCreateView(inflater, container, savedInstanceState)
        Log.i("Fragment", "onCreateView called(Motivation)")

    return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        val motivationList = listOf<String>(
            "Niemand, der sein Bestes gegeben hat, hat es später bereut.", "Dein Körper kann alles schaffen. Es ist dein Geist, den du überzeugen musst.",
            "Ohne Fleiß kein Preis", "Egal wie weit der Weg ist, man muß den ersten Schritt tun.", "Jeder Champion war mal ein Kämpfer, der sich geweigert hat aufzugeben.",
            "Es gibt kein “Ich kann das nicht”. Höchstens ” Ich kann das noch nicht.", "PUSH: persist until something happens", "Wenn du aufgeben willst denk, darüber nach warum du angefangen hast.",
            "Gestern hast du morgen gesagt.", "Stop wishing. Start doing.", "Es ist egal, wie langsam du vorankommst. Du überrundest noch immer jeden auf dem Sofa.",
            "Ein Ziel ist ein Traum mit Termin.", "Warte nicht. Es gibt nie den passenden Moment.",
            "Wenn du ganz unten bist. führt der einzige Weg nach oben.", "Dein Leben beginnt dort, wo deine Komfortzone endet.", "Alles, was du brauchst, um erfolgreich zu sein, hast du bereits in dir.",
            "Mut steht am Anfang des Handelns, Glück am Ende.", "Stolpere nicht über etwas, das hinter dir liegt.", "Alles ist schwierig, bevor es einfach ist.",
            "Cowards never start. The weak never finish. Winner never quit.", "Wer immer tut, was er schon kann, bleibt immer das, was er schon ist.",
            "Wir sind nicht auf der Welt, um so zu sein, wie andere uns haben wollen.", "Selbst der schlimmste Fehler ist nicht so schlimm, wie es nie versucht zu haben.",
            "Nimm dein Leben in deine Hand. Sonst werden es andere für dich tun.", "Am Ende der Ausreden beginnt dein Leben.",
            "Probleme kann man nie mit der selben Denkweise lösen, durch die sie entstanden sind.", "Du bist nie zu alt, um einen neuen Traum zu träumen.",
            "Wer die Ruhe nicht in sich selbst findet, wird sie auch anderswo vergeblich suchen.", "Es gibt nur eine Art von Erfolg: sein Leben so zu führen, wie man möchte.",
            "Jeder Mensch mit einer neuen Idee ist ein Spinner. Bis die Idee Erfolg hat.", "Ein Schiff ist sicherer, wenn es im Hafen liegt. Doch dafür werden Schiffe nicht gebaut.",
            "Probleme sind Gelegenheiten zu zeigen, was du kannst.", "Du hast ein paar Feinde? Gut, das bedeutet, dass du für etwas aufgestanden bist.",
            "Wir leben alle unter dem gleichen Himmel. Aber wir haben nicht alle den gleichen Horizont.", "Wer kämpft kann verlieren, wer nicht kämpft, hat schon verloren.",
            "Das Schicksal liegt nicht in der Hand des Zufalls, es liegt in deiner Hand, du sollst nicht darauf warten, du sollst es bezwingen.",
            "Wer den Hafen nicht kennt, in den er segeln will, für den ist kein Wind ein günstiger.", "Es ist besser, ein kleines Licht anzuzünden, als über die Dunkelheit zu schimpfen."
        ).random()

        val tipList = listOf<String>()

        ZitatText.text = "\"" + motivationList + "\""


        // gets the current date as well as yesterday's date and checks if they are the same. (checks if we have a new day)
        /*
        val zonedYesterday = ZonedDateTime.now(ZoneId.of("Europe/Berlin")).minusDays(1).toLocalDate()
        val zonedFormattedYesterday = zonedYesterday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))

        val DateOfToday = zonedFormattedToday

        if (DateOfToday.compareTo(zonedFormattedToday) == 0){
            ZitatText.text = "selber tag"
       }
        else{
            ZitatText.text = tempVariable
        }

         */

    }


    companion object {
        fun newInstance() = FragmentMotivation()
    }
}

package com.example.habits.Motivation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.R
import com.example.habits.ui.adapters.TipAdapter
import kotlinx.android.synthetic.main.fragment_motivation.*

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


    return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        val tipList = arrayListOf<String>("Am Besten unterteilt man große Ziele in mehrere Teilziele, dadurch kann man eher Erfolge erzielen. Das motiviert und hilft am Ball zu bleiben.",
            "Der Fortschritt beim Ziele erreichen soll messbar sein.", "Im Optimalfall muss das Ziel durch eigene Aktionen erreichbar sein, denn Ziele die Fremdeinwirkung benötigen liegen nicht in deiner Kontrolle",
            "Ziele realistisch formulieren. \"2020 bin ich der erste Mensch auf dem Saturn\" ist kein gut formuliertes Ziel.", "Das Ziel hat ein Datum. Also eine klare Deadline, zu der es erreicht sein soll.",
            "Analysiere deinen Tagesablauf und überlege an welchen Stellen du Zeit verschwendest. Wenn du etwas unbedingt erreichen möchtest dann wirst du Zeit dazu finden.")

        tipRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        tipRecyclerView.adapter = TipAdapter(tipList)

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
        motivationText.text = "\"" + motivationList + "\""

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
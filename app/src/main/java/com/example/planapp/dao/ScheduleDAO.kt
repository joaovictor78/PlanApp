import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.corretagemapp.database.DataBase
import com.example.planapp.models.DateScheduleModel
import com.example.planapp.models.ScheduleModel
import java.util.*
import kotlin.collections.ArrayList

class ScheduleDAO(context: Context?) {
    private var db: SQLiteDatabase? = null
    private val database: DataBase
    @Throws(Exception::class)
    fun insertCompromisso(compromisso: ScheduleModel) {
        val contentValuesCompromisso: ContentValues
        val contentValuesDataCompromisso: ContentValues
        val resultCompromisso: Long
        val resultDataCompromisso: Long
        db = database.getWritableDatabase()
        contentValuesCompromisso = ContentValues()
        contentValuesDataCompromisso = ContentValues()
        contentValuesCompromisso.put("assunto", compromisso.topic)
        contentValuesCompromisso.put("horario", compromisso.time)
        contentValuesCompromisso.put("descricao", compromisso.description)
        resultCompromisso = db!!.insert("compromisso", null, contentValuesCompromisso)
        contentValuesDataCompromisso.put("id_compromisso", resultCompromisso)
        contentValuesDataCompromisso.put("data", compromisso.date)
        resultDataCompromisso = db!!.insert("data_compromisso", null, contentValuesDataCompromisso)
        if (resultCompromisso == -1L || resultDataCompromisso == -1L) {
            throw Exception("Erro ao cadastrar compromisso!")
        }
        db!!.close()
    }

    val allDateCompromissos: List<Any?>
        get() {
            db = database.getReadableDatabase()
            val selectDataAllQuery = "SELECT * FROM data_compromisso;"
            val cursor: Cursor = db!!.rawQuery(selectDataAllQuery, null)
            val dataCompromisso: MutableList<DateScheduleModel?> = ArrayList()
            while (cursor.moveToNext()) {
                val valor: String = cursor.getString(cursor.getColumnIndex("data"))
                val dia = valor.substring(0, 2)
                val mes = valor.substring(3, 5)
                val ano = valor.substring(6, 10)
                val data = DateScheduleModel(dia, mes, ano)
                dataCompromisso.add(data)
            }
            cursor.close()
            db!!.close()
            return dataCompromisso
        }

    fun getCompromissos(date: String): List<ScheduleModel?> {
        db = database.getReadableDatabase()
        val seletAllCompromissosByDate = "SELECT * FROM compromisso INNER JOIN data_compromisso ON compromisso.codigo_compromisso = data_compromisso.id_compromisso WHERE data_compromisso.data ='$date';"
        val cursor: Cursor = db!!.rawQuery(seletAllCompromissosByDate, null)
        val listCompromisso: MutableList<ScheduleModel?> = ArrayList()
        while (cursor.moveToNext()) {
            val id_compromisso: String = cursor.getString(cursor.getColumnIndex("codigo_compromisso"))
            val assunto: String = cursor.getString(cursor.getColumnIndex("assunto"))
            val horario: String = cursor.getString(cursor.getColumnIndex("horario"))
            val descricao: String = cursor.getString(cursor.getColumnIndex("descricao"))
            val compromisso = ScheduleModel()
            compromisso.topic = descricao
            compromisso.time = horario
            compromisso.description = descricao
            listCompromisso.add(compromisso)
        }
        return listCompromisso
    }

    init {
        database = DataBase(context)
    }
}



package com.proyecto.bbdd


import androidx.room.Database
import androidx.room.RoomDatabase
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasClan
import com.proyecto.bbdd.entity.NNClanDisciplinas
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.interfaces.DisciplinasClanDao
import com.proyecto.bbdd.interfaces.NNClanDisciplinasDao
import com.proyecto.bbdd.interfaces.PoderesDao
import com.proyecto.bbdd.interfaces.UsuarioDao
import com.proyecto.bbdd.interfaces.VastagoDao


@Database(entities = [Usuario:: class , Vastago:: class, Clan:: class, DisciplinasClan:: class, NNClanDisciplinas:: class,Poderes:: class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {
    abstract val dao: UsuarioDao
    abstract val vasDao: VastagoDao
    abstract val clanDao: ClanDao
    abstract val disciplinasClanDao: DisciplinasClanDao
    abstract val NNClanDisciplinasDao: NNClanDisciplinasDao
    abstract val poderesDao: PoderesDao
}
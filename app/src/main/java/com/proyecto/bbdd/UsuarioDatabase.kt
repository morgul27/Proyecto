package com.proyecto.bbdd


import androidx.room.Database
import androidx.room.RoomDatabase
import com.proyecto.bbdd.entity.Amalgama
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasClan
import com.proyecto.bbdd.entity.DisciplinasVas
import com.proyecto.bbdd.entity.NNClanDisciplinas
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.entity.PoderesVas
import com.proyecto.bbdd.entity.Usuario
import com.proyecto.bbdd.entity.Vastago
import com.proyecto.bbdd.interfaces.AmalgamaDao
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.interfaces.DisciplinasClanDao
import com.proyecto.bbdd.interfaces.DisciplinasVasDao
import com.proyecto.bbdd.interfaces.NNClanDisciplinasDao
import com.proyecto.bbdd.interfaces.PoderesDao
import com.proyecto.bbdd.interfaces.PoderesVasDao
import com.proyecto.bbdd.interfaces.UsuarioDao
import com.proyecto.bbdd.interfaces.VastagoDao


@Database(
    entities = [
        Amalgama:: class,
        Clan:: class,
        DisciplinasClan:: class,
        DisciplinasVas:: class,
        NNClanDisciplinas:: class,
        Poderes:: class,
        PoderesVas:: class,
        Usuario:: class,
        Vastago:: class
               ], version = 1
) abstract class UsuarioDatabase: RoomDatabase() {
    abstract val amalgamaDao: AmalgamaDao
    abstract val clanDao: ClanDao
    abstract val disciplinasClanDao: DisciplinasClanDao
    abstract val disciplinasVasDao: DisciplinasVasDao
    abstract val nnClanDisciplinasDao: NNClanDisciplinasDao
    abstract val poderesDao: PoderesDao
    abstract val poderesVAsDao: PoderesVasDao
    abstract val dao: UsuarioDao
    abstract val vasDao: VastagoDao

}
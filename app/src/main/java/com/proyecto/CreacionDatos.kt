package com.proyecto

import com.proyecto.bbdd.entity.Amalgama
import com.proyecto.bbdd.entity.Clan
import com.proyecto.bbdd.entity.DisciplinasClan
import com.proyecto.bbdd.entity.NNClanDisciplinas
import com.proyecto.bbdd.entity.Poderes
import com.proyecto.bbdd.interfaces.AmalgamaDao
import com.proyecto.bbdd.interfaces.ClanDao
import com.proyecto.bbdd.interfaces.DisciplinasClanDao
import com.proyecto.bbdd.interfaces.NNClanDisciplinasDao
import com.proyecto.bbdd.interfaces.PoderesDao

class CreacionDatos {

    // Función para insertar datos iniciales
     suspend fun insertInitialDataClan(dao: ClanDao) {
        // Insertar los datos
        dao.insertClan(Clan(1, nombreClan = "Nosferatu"))
        dao.insertClan(Clan(2, nombreClan = "Gangrel"))
        dao.insertClan(Clan(3, nombreClan = "Brujah"))

    }
     suspend fun insertInitialDataDisciplinas(dao: DisciplinasClanDao) {
        // Nosferatu
        dao.insertDisciplinasClan(DisciplinasClan(1, nombre = "Animalismo"))
        dao.insertDisciplinasClan(DisciplinasClan(2, nombre = "Ofuscación"))
        dao.insertDisciplinasClan(DisciplinasClan(3, nombre = "Potencia"))
        //Gangrel
        dao.insertDisciplinasClan(DisciplinasClan(4, nombre = "Fortaleza"))
        dao.insertDisciplinasClan(DisciplinasClan(5, nombre = "Protean"))
        //Brujah
        dao.insertDisciplinasClan(DisciplinasClan(6, nombre = "Celeridad"))
        dao.insertDisciplinasClan(DisciplinasClan(7, nombre = "Presencia"))

    }
     suspend fun insertNNClanDisciplinas(dao: NNClanDisciplinasDao) {
        // Nosferatu
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 1)) //Animalismo
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 2)) //Ofuscación
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 1, fk_disc = 3)) //Potencia
        //Gangrel
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 1)) //Animalismo
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 4)) //Fortaleza
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 2, fk_disc = 5)) //Protean
        //Brujah
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 3)) //Potencia
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 6)) //Celeridad
        dao.insertNNClanDisciplinas(NNClanDisciplinas(fk_clan = 3, fk_disc = 7)) //Presencia
    }
     suspend fun insertPoderes(dao: PoderesDao) {
        // Animalismo
        dao.insertPoderes(Poderes(id = 1, nombre = "sentir a la bestia"))
        dao.insertPoderes(Poderes(id = 2, nombre = "vínculo con famulus"))
        dao.insertPoderes(Poderes(id = 3, nombre = "susurros salvajes")) //lvl2
        dao.insertPoderes(Poderes(id = 4, nombre = "colmena no-muerta")) //lvl3
        dao.insertPoderes(Poderes(id = 5, nombre = "reprimir a la bestia"))
        dao.insertPoderes(Poderes(id = 6, nombre = "suculencia animal"))
        dao.insertPoderes(Poderes(id = 7, nombre = "comunión de espíritus"))//lvl4
        dao.insertPoderes(Poderes(id = 8, nombre = "control animal")) //lvl5
        dao.insertPoderes(Poderes(id = 9, nombre = "expulsar a la bestia"))

        //Ofuscación
        dao.insertPoderes(Poderes(id = 10, nombre = "capa de sombras"))
        dao.insertPoderes(Poderes(id = 11, nombre = "silencio de la muerte"))
        dao.insertPoderes(Poderes(id = 12, nombre = "paso invisible")) //lvl2
        dao.insertPoderes(Poderes(id = 13, nombre = "fantasma en la máquina")) //lvl3
        dao.insertPoderes(Poderes(id = 14, nombre = "máscara de las mil caras"))
        dao.insertPoderes(Poderes(id = 15, nombre = "desvanecerse")) //lvl4
        dao.insertPoderes(Poderes(id = 16, nombre = "ocultar"))
        dao.insertPoderes(Poderes(id = 17, nombre = "disfraz del impostor")) //lvl5
        dao.insertPoderes(Poderes(id = 18, nombre = "encubrimiento de la concurrencia"))

        //Potencia
        dao.insertPoderes(Poderes(id = 19, nombre = "cuerpo letal"))
        dao.insertPoderes(Poderes(id = 20, nombre = "salto vertiginoso"))
        dao.insertPoderes(Poderes(id = 21, nombre = "bravura")) //lvl2
        dao.insertPoderes(Poderes(id = 22, nombre = "agarre asombroso")) //lvl3
        dao.insertPoderes(Poderes(id = 23, nombre = "alimentación brutal"))
        dao.insertPoderes(Poderes(id = 24, nombre = "chispa de ira"))
        dao.insertPoderes(Poderes(id = 25, nombre = "sorbo de poderío")) //lvl4
        dao.insertPoderes(Poderes(id = 26, nombre = "puño de caín")) //lvl5
        dao.insertPoderes(Poderes(id = 27, nombre = "temblor de tierra"))

        //Fortaleza
        dao.insertPoderes(Poderes(id = 28, nombre = "mente imperturbable"))
        dao.insertPoderes(Poderes(id = 29, nombre = "resiliencia"))
        dao.insertPoderes(Poderes(id = 30, nombre = "bestias resistentes")) //lvl2
        dao.insertPoderes(Poderes(id = 31, nombre = "dureza"))
        dao.insertPoderes(Poderes(id = 32, nombre = "desafiar prohibición")) //lvl3
        dao.insertPoderes(Poderes(id = 33, nombre = "fortificar la fachada interior"))
        dao.insertPoderes(Poderes(id = 34, nombre = "sorbo de aguante")) //lvl4
        dao.insertPoderes(Poderes(id = 35, nombre = "arrojo por el dolor")) //lvl5
        dao.insertPoderes(Poderes(id = 36, nombre = "carne de mármol"))

        //Protean
        dao.insertPoderes(Poderes(id = 37, nombre = "ojos de la bestia"))
        dao.insertPoderes(Poderes(id = 38, nombre = "peso de la pluma"))
        dao.insertPoderes(Poderes(id = 39, nombre = "armas salvajes")) //lvl2
        dao.insertPoderes(Poderes(id = 40, nombre = "cambiar de forma")) //lvl3
        dao.insertPoderes(Poderes(id = 41, nombre = "fusión con la tierra"))
        dao.insertPoderes(Poderes(id = 42, nombre = "metamorfosis")) //lvl4
        dao.insertPoderes(Poderes(id = 43, nombre = "corazón liberado")) //lvl5
        dao.insertPoderes(Poderes(id = 44, nombre = "forma de niebla"))

        //Celeridad
        dao.insertPoderes(Poderes(id = 45, nombre = "gracia felina"))
        dao.insertPoderes(Poderes(id = 46, nombre = "reflejos rápidos"))
        dao.insertPoderes(Poderes(id = 47, nombre = "presteza")) //lvl2
        dao.insertPoderes(Poderes(id = 48, nombre = "pestañeo")) //lvl3
        dao.insertPoderes(Poderes(id = 49, nombre = "recorrido"))
        dao.insertPoderes(Poderes(id = 50, nombre = "puntería certera")) //lvl4
        dao.insertPoderes(Poderes(id = 51, nombre = "sorbo de elegancia"))
        dao.insertPoderes(Poderes(id = 52, nombre = "golpe relámpago")) //lvl5
        dao.insertPoderes(Poderes(id = 53, nombre = "segundo quebrado"))

        //Presencia
        dao.insertPoderes(Poderes(id = 54, nombre = "atemorizar"))
        dao.insertPoderes(Poderes(id = 55, nombre = "fascinación"))
        dao.insertPoderes(Poderes(id = 56, nombre = "beso persistente")) //lvl2
        dao.insertPoderes(Poderes(id = 57, nombre = "encantamiento")) //lvl3
        dao.insertPoderes(Poderes(id = 58, nombre = "mirada aterradora"))
        dao.insertPoderes(Poderes(id = 59, nombre = "invocación")) //lvl4
        dao.insertPoderes(Poderes(id = 60, nombre = "voz irresistible"))
        dao.insertPoderes(Poderes(id = 61, nombre = "magnetismo de estrella")) //lvl5
        dao.insertPoderes(Poderes(id = 62, nombre = "majestad"))

    }

     suspend fun insertInitialDataAmalgama(dao: AmalgamaDao) {
        //Animalismo
        // sentir a la bestia
        dao.insertAmalgama(
            Amalgama(
                1,
                fkvas_poder = 1,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 1
            )
        )

        //vínculo con famulus
        dao.insertAmalgama(
            Amalgama(
                2,
                fkvas_poder = 2,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 1
            )
        )

        //susurros salvajes
        dao.insertAmalgama(
            Amalgama(
                3,
                fkvas_poder = 3,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 2
            )
        )

        //colmena no-muerta
        dao.insertAmalgama(
            Amalgama(
                4,
                fkvas_poder = 4,
                fkvas_disciplina_principal = 1,
                fkvas_disciplina_secundaria = 2, //Ofuscacion
                nivel_disciplina_principal = 3,
                nivel_disciplina_secundaria = 2
            )
        )

        //reprimir a la bestia
        dao.insertAmalgama(
            Amalgama(
                5,
                fkvas_poder = 5,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 3
            )
        )

        //suculencia animal
        dao.insertAmalgama(
            Amalgama(
                6,
                fkvas_poder = 6,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 3
            )
        )

        //comunión de espíritus
        dao.insertAmalgama(
            Amalgama(
                7,
                fkvas_poder = 7,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 4
            )
        )

        //control animal
        dao.insertAmalgama(
            Amalgama(
                8,
                fkvas_poder = 8,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 5
            )
        )

        //expulsar a la bestia
        dao.insertAmalgama(
            Amalgama(
                9,
                fkvas_poder = 9,
                fkvas_disciplina_principal = 1,
                nivel_disciplina_principal = 5,
            )
        )

        //Ofuscación
        //capa de sombras


    }
}
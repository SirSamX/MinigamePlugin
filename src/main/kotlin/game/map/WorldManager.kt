package me.sirsam.minigameplugin.game.map

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.codehaus.plexus.util.FileUtils
import java.io.*


class WorldManager { // FIXME: TEST FOR REMOVAL
    fun resetWorld(gameID: Int) {
        val original = MinigameWorld.BEDWARS.world
        copyWorld(original, gameID.toString())
    }

    private fun copyFileStructure(source: File, target: File) {
        try {
            val ignore: ArrayList<String> = ArrayList(listOf("uid.dat", "session.lock"))
            if (!ignore.contains(source.getName())) {
                if (source.isDirectory()) {
                    if (!target.exists()) if (!target.mkdirs()) throw IOException("Couldn't create world directory!")
                    val files = source.list()
                    for (file in files!!) {
                        val srcFile = File(source, file)
                        val destFile = File(target, file)
                        copyFileStructure(srcFile, destFile)
                    }
                } else {
                    val `in`: InputStream = FileInputStream(source)
                    val out: OutputStream = FileOutputStream(target)
                    val buffer = ByteArray(1024)
                    var length: Int
                    while (`in`.read(buffer).also { length = it } > 0) out.write(buffer, 0, length)
                    `in`.close()
                    out.close()
                }
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun copyWorld(originalWorld: World?, newWorldName: String?): World? {
        val copiedFile = File(Bukkit.getWorldContainer(), newWorldName!!)
        copyFileStructure(originalWorld!!.worldFolder, copiedFile)
        return WorldCreator(newWorldName).createWorld()
    }

    fun removeAllTempWorlds() {
        for (i in 1..5) {
            val tempFile = File(Bukkit.getWorldContainer(), i.toString())
            if (tempFile.exists()) {
                try {
                    Bukkit.getServer().unloadWorld(Bukkit.getWorld(i.toString())!!, false)
                    FileUtils.deleteDirectory(tempFile)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
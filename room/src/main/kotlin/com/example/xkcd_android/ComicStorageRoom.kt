package com.example.xkcd_android

class ComicStorageRoom(private val comicDaoRoom: ComicDaoRoom) : ComicStorageLocal {
    override fun comic(number: Int): Comic {
        val comicEntityRoom = comicDaoRoom.comic(number)
        return comicEntityRoom.toComic()
    }

    override fun save(comic: Comic) {
        val comicEntityRoom = comic.toComicEntityRoom()
        comicDaoRoom.save(comicEntityRoom)
    }

    private companion object {
        fun ComicEntityRoom.toComic(): Comic {
            return Comic(month = month, num = num, link = link, year = year, news = news,
                safeTitle = safeTitle, transcript = transcript, alt = alt, img = img, title = title,
                day = day)
        }

        fun Comic.toComicEntityRoom(): ComicEntityRoom {
            return ComicEntityRoom(month = month, num = num, link = link, year = year, news = news,
                safeTitle = safeTitle, transcript = transcript, alt = alt, img = img, title = title,
                day = day)
        }
    }
}
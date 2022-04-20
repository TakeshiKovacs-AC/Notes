import Exceptions.CommentNotFoundException
import Exceptions.NoteNotFoundException

class Notes {

    private var listOfNotes = arrayListOf<Note>()
    private var listOfComments = arrayListOf<Comment>()
    private var listOfDeletedComments = arrayListOf<Comment>()

    fun add(note: Note): Note {            //добавление заметки в список
        listOfNotes += note
        if (listOfNotes.isEmpty()) {
            note.nId = 1
        } else {
            note.nId = listOfNotes.last().nId + 1
        }
        return note
    }

    fun createComment(comment: Comment): Comment {     //добавление нового комментария к заметке
        for (findNote in listOfNotes) {
            if (comment.noteId == findNote.nId) {
                listOfComments += comment
            }
        }
        if (!listOfComments.contains(comment)) {
            throw NoteNotFoundException("Такой заметки не существует!")
        }
        if (listOfComments.isEmpty()) {
            comment.cId = 1
        } else {
            comment.cId = listOfComments.last().cId + 1
        }
        return comment
    }


    fun delete(note: Note): Boolean {           //удаление заметки
        for (findNote in listOfNotes) {
            if (note.nId == findNote.nId) {
                listOfNotes.remove(note)
                return true
            }
        }
        if (!listOfNotes.contains(note))
            throw NoteNotFoundException("Такой заметки не существует!")
        return false
    }

    fun deleteComment(comment: Comment): Boolean {        //удаление коммента к заметке
        for (findComment in listOfComments) {
            if (comment.cId == findComment.cId) {
                listOfComments.remove(findComment)
                listOfDeletedComments += comment
                return true
            }
        }
        if (!listOfComments.contains(comment))
            throw CommentNotFoundException("Такого комментария не существует!")
        return false
    }

    fun edit(note: Note): Boolean {                   //редактирование заметки
        for (findNote in listOfNotes) {
            if (note.nId == findNote.nId) {
                findNote.title = note.title
                findNote.text = note.text
                return true
            }
        }
        if (!listOfNotes.contains(note))
            throw NoteNotFoundException("Невозможно исправить несуществующий или удалённый пост!")
        return false
    }

    fun editComment(comment: Comment): Boolean {      //редактирование коммента
        for (findComment in listOfComments) {
            if (comment.cId == findComment.cId) {
                findComment.message = comment.message
                return true
            }
        }
        if (!listOfComments.contains(comment))
            throw CommentNotFoundException("Невозможно исправить несуществующий или удалённый комментарий!")
        return false
    }

    fun getNotes() {
        println(listOfNotes)
    }

    fun getById(nId: Int): Note? {                                    //Возвращает заметку по её id
        val oneMap = listOfNotes.associate { Pair(it.nId, it.text) }
        for (findNote in listOfNotes) {
            if (findNote.nId == nId) {
                return findNote
            }
        }
        for ((key) in oneMap) {
            if (key != nId)
                throw NoteNotFoundException("Такой заметки не существует!")
        }
        return null
    }

    fun getComments(note: Note): Array<Comment>? {                //Возвращает список комментариев к заметке
        var commentsOfNotes = emptyArray<Comment>()
        for (findNote in listOfNotes) {
            if (findNote.nId == note.nId) {
                for (findComment in listOfComments) {
                    if (findComment.noteId == note.nId) {
                        commentsOfNotes += findComment
                        return commentsOfNotes
                    }
                }
                if (commentsOfNotes.isEmpty())
                    throw CommentNotFoundException("К данной заметке нет комментариев!")
            }
        }
        if (!listOfNotes.contains(note))
            throw NoteNotFoundException("Такой заметки не существует!")
        return null
    }

    fun restoreComment(comment: Comment): Comment? {              //восстановление удаленного комментария
        for (findDeleteComment in listOfDeletedComments) {
            if (findDeleteComment.cId == comment.cId) {
                listOfDeletedComments.remove(comment)
                listOfComments += comment
                return comment
            }
        }
        if (!listOfDeletedComments.contains(comment))
            throw CommentNotFoundException("Такой коммент отсутствует в удалённых!")
        return null
    }
}

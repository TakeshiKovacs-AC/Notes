import Exceptions.CommentNotFoundException
import Exceptions.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*

class NotesTest {

    @Test
    fun add() {
        //arrange
        val tit = "Arrange"
        val txt = "Act and Assert"
        val id = 2

        //act
        val notes = Notes()
        val checkedNote = notes.add(Note(
            title = tit,
            text = txt,
            nId = id
        ))

        //assert
        assertNotEquals(id, checkedNote.nId)
    }

    @Test
    fun createComment() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val comment1 = notes.createComment(Comment(
            3, "Good", 1
        ))

        //assert
        assertEquals(note1.nId, comment1.noteId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentWithExc() {

        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val comment2 = notes.createComment(Comment(
            5,"Very good", 2
        ))

        //arrange
        if(note2.nId != comment2.noteId)
            throw NoteNotFoundException("Fail")
    }

    @Test
    fun delete() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val note3 = notes.add(Note(
            "Many thanks", "Grateful to you", 4
        ))

        val result = notes.delete(note2)
        //assert

        assertEquals(true, result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteWithExc() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
                "Hello", "Hello everyone", 2
            ))
        val note2 = notes.add(Note(
                "Goodbye", "Goodbye everyone", 3
            ))
        val note3 = notes.add(Note(
                "Many thanks", "Grateful to you", 4
            ))
        val note4 = Note("Queue", "Start to study Queue", 5)

        val result = notes.delete(note4)

        //assert

        if(result)
            throw NoteNotFoundException("The end!")
    }

    @Test
    fun deleteComment() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val comment1 = notes.createComment(Comment(
            3, "Good", 1
        ))
        val comment2 = notes.createComment(Comment(
            4,"Very good", 2
        ))
        val result = notes.deleteComment(comment2)

        //assert
        assertEquals(true, result)
    }

    @Test(expected = CommentNotFoundException:: class)
    fun deleteCommentWithExc() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val comment1 = notes.createComment(Comment(
            3, "Good", 1
        ))
        val comment2 = notes.createComment(Comment(
            4,"Very good", 2
        ))
        val comment3 = Comment(8, "Not so good", 3)

        val result = notes.deleteComment(comment3)

        //assert
        if(result)
            throw CommentNotFoundException("The end!")
    }

    @Test
    fun edit() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val edit = notes.edit(Note(
            "Not now", "It is forbidden now", 4)
        )

        //assert
        assertEquals(true, edit)

    }

    @Test(expected = NoteNotFoundException:: class)
    fun editWithExc() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(
            Note(
                "Hello", "Hello everyone", 2
            ))
        val note2 = notes.add(
            Note(
                "Goodbye", "Goodbye everyone", 3
            ))
        val edit = notes.edit(
            Note(
                "Not now", "It is forbidden now", 4
            ))

        //assert
        if (edit)
            throw(NoteNotFoundException("Finish"))

    }
    @Test
    fun editComment() {
        //arrange
        val notes = Notes()

        //act
        val note = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val comment = notes.createComment(Comment(
            3, "Not so good", 3
        ))
        val edit = Comment(5, "111", 4)
        val result = notes.editComment(edit)

        //assert
        assertEquals(true, result)
    }

    @Test(expected = CommentNotFoundException :: class)
    fun editCommentWithExc() {
        //arrange
        val notes = Notes()

        //act
        val note = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val comment = notes.createComment(Comment(
            3, "Not so good", 3
        ))
        val edit = Comment(5, "111", 4)
        val result = notes.editComment(edit)

        //assert
        if (result)
            throw CommentNotFoundException("!")
    }

    @Test
    fun getById() {

        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(
            Note(
                "Hello", "Hello everyone", 2
            ))
        val note2 = notes.add(
            Note(
                "Goodbye", "Goodbye everyone", 3
            ))
        val identification = 3

    //assert
    assertEquals(identification, note1.nId)
}
    @Test(expected = NoteNotFoundException :: class)
    fun getByIdWithExc() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(
            Note(
                "Hello", "Hello everyone", 2
            ))
        val note2 = notes.add(
            Note(
                "Goodbye", "Goodbye everyone", 3
            ))
        val identification = 6

        //assert
        if (identification != note1.nId && identification != note2.nId)
            throw NoteNotFoundException("!")
    }

    @Test
    fun getComments() {
        //arrange
        val notes = Notes()

        //act
        val note1 = notes.add(Note(
            "Hello", "Hello everyone", 2
        ))
        val note2 = notes.add(Note(
            "Goodbye", "Goodbye everyone", 3
        ))
        val comment1 = notes.createComment(Comment(
            4,"Very good", 2
        ))
        val comment2 = notes.createComment(Comment(
            4,"Very cool", 4
        ))

        //assert
        assertEquals(comment2.noteId, note2.nId)
    }

    @Test
    fun restoreComment() {
    }
}
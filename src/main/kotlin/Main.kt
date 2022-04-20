fun main() {
val notes = Notes()

    val note1 = Note(
        "Hello", "Hello all", 0
    )

    val note2 = Note(
        "Good day", "Good day everyone", 1
    )

    val note3 = Note(
        "Many tnx", "Many thanks", 2
    )

    val comment1 = Comment(
        1, "Cool", 1
    )

    val comment2 = Comment(
        2, "Not bad", 1
    )

    val comment3 = Comment(
        3, "Let's try", 4
    )

    val noteEdit = Note("Hi", "Kong", 2)
    val commentEdit = Comment(3, "Y", 2)

    notes.add(note1)
    notes.add(note2)
    notes.add(note3)
    notes.createComment(comment1)
    notes.createComment(comment2)
    notes.createComment(comment3)
    notes.delete(note3)
    notes.deleteComment(comment3)
    notes.edit(noteEdit)
    notes.editComment(commentEdit)
    notes.getNotes()
    println(notes.getById(8))
    println(notes.getComments(note1).contentToString())
    notes.restoreComment(comment3)

//    for (note in notes.listOfNotes)
//        println(note)
//    println()
//
//    for (comment in notes.listOfComments)
//        println(comment)
//    println()
}
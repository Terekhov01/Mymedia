function deleteMedia(mediaId) {
    $.ajax({
        url: 'localhost:8080/library' + mediaId,
        type: 'DELETE',
        success: function(result) {
            var newDoc = document.open("text/html", "replace");
            newDoc.write(result);
            newDoc.close();
        }
    });
}
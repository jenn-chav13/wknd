use(function() {
    let youtubeVideoObject = {};

    youtubeVideoObject.ytVideo = properties["videoId"];

    fetch(`https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=${youtubeVideoObject.ytVideo}&format=json`)           //api for the get request
        .then(response => response.json())
        .then(data => {
            youtubeVideoObject.ytTitle = data.title;
            youtubeVideoObject.ytAuthorName = data.author_name;
        });

    return youtubeVideoObject;

});
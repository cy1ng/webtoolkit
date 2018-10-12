$(document).ready(function () {  
    
    $('#id_uploadify').uploadify({
        'swf'      : 'images/uploadify/uploadify.swf',
        'uploader' : '/webtoolkit/upload.do',
        'uploadLimit'     : 10,  
         'queueID'         : 'fileQueue'  
        // Put your options here
    });

});  
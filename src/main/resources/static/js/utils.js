function createRequest(url,method,payload,functionDone,functionFail) {
    let ajaxRequest={
        url: location.origin + url,
        type: method,
        data: JSON.stringify(payload),
        dataType: 'json',
        contentType: "application/json"
    };
    if(payload==null || payload===''){
        delete ajaxRequest.data;
    }
    $.ajax(ajaxRequest).done(function(data){
        functionDone(data);
    }).fail(function(data){
        functionFail(data);
    });
}

function checkIfKeyExistsOnCache(key){
    return check(localStorage.getItem(key));
}

function getObjectFromCache(key){
    return JSON.parse(localStorage.getItem(key));
}

function putObjectIntoCache(key,object){
    localStorage.setItem(key,JSON.stringify(object));
}

function alertFail(data){
    alert(data);
}

function check(variable){
    return !(typeof variable === "undefined" || variable === null) ;
}


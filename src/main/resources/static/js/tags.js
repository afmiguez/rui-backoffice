$(function () {
    if(!checkIfKeyExistsOnCache("tags")){
        createRequest("/tags", "GET", null, getTags, function (data){console.log(data)});
    }
    renderTags();

    installListenerForTagDropdown();

    installListenerForAddButton();

    installListenerForSaveButton();

});

function installListenerForSaveButton(){
    $("#guardar_tag").on('click',function (){
        let tags=getObjectFromCache("tags");
        createRequest("/tags","PUT",tags,function (){},alertFail);
    })
}

function installListenerForAddButton(){
    $("#adicionar_tag").on('click',function (){

        let tag={
            name:$("#name").val(),
            desc:$("#desc").val()
        };
        addOrUpdateTagLocalStorage(tag);
    });
}

function installListenerForTagDropdown(){
    $("#tags").on('change',function(){
        let name=this.value;
        let tag=getTagFromLocalStorage(name);
        $("#name").val(tag.name);
        $("#desc").val(tag.desc);
    });
}

function addOrUpdateTagLocalStorage(tag){
    let allTags=getObjectFromCache("tags");
    if(check(getTagFromLocalStorage(tag.name))){
        allTags= allTags.filter(function(returnableObjects){
            return returnableObjects.name.toLowerCase() !== tag.name.toLowerCase();
        });
    }
    allTags.push(tag);

    putObjectIntoCache("tags",allTags);
}

function getTagFromLocalStorage(name) {
    let allTags=getObjectFromCache("tags");
    for(let i=0;i<allTags.length;i++){
        let tag=allTags[i];
        if(tag.name.toLowerCase()===name.toLowerCase()){
            return tag;
        }
    }
}

function renderTags(){
    let data=getObjectFromCache("tags");
    let tagsSel=$("#tags");
    $.each(data,function (i, tag){
        tagsSel.append($('<option>',{
            value:tag.name.toLowerCase(),
            text: tag.name.toLowerCase()
        }));
    });
}

function getTags(data){
    putObjectIntoCache("tags",data);
    renderTags();
}


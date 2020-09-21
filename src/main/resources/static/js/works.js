$(function (){
    createRequest("/works",'GET',null,renderInformation,alertFail);
    createRequest("/tags","GET",null,renderTagsDropDown,alertFail);
    $('#tags').multiSelect();

    installListenerForWorksDropdown();

    installListenerForSaveWorksButton();
});

function installListenerForSaveWorksButton(){
    $("#editWork").on('click',function (){
        let fields=$('#editFields').find('input:text');
        let tags=$("#tags").val();
        let work=new Work(fields,tags);
        createRequest("/works","PUT",work,
            function (data){},
            alertFail
        );
    });
}

function installListenerForWorksDropdown(){
    $("#worksDropDown").on('change',function (){
        clear_fetch();
        let title=this.value;

        let allWorks=getObjectFromCache("works");
        $.each(allWorks,function (i,work){
            if(work.title===title){
                populateTextfields(work);
            }
        });
    });
}

function clear_fetch() {
    $('#editFields').find('input:text').val('');
    let tagsSel=$('#tags');
    tagsSel.multiSelect('deselect_all');
    tagsSel.multiSelect('refresh');
}

function populateTextfields(work){

    if( check(work.authorData[0])&& check(work.authorData[0].first))
        $("#authorFirst").val(work.authorData[0].first)
    if(check(work.authorData[0]) && check(work.authorData[0].last))
        $("#authorLast").val(work.authorData[0].last)
    if(check(work.title))
        $("#title").val(work.title);
    if(check(work.location.city))
        $("#city").val(work.location.city);
    if(check(work.location.country))
        $("#country").val(work.location.country);
    if(check(work.language))
        $("#language").val(work.language.join(","));
    if(check(work.url))
        $("#url").val(work.url);
    if(check(work.icon))
        $("#icon").val(work.icon);
    if(check(work.tags)){
        let tagsSel=$('#tags');
        tagsSel.multiSelect('select',work.tags.map(name => name.toLowerCase()));
        tagsSel.multiSelect('refresh');
    }
    if(check(work.beginLinks[0]) && check(work.beginLinks[0].link))
        $("#beginLinks").val(work.beginLinks[0].link);
    if(check(work.instructions))
        $("#instructions").val(work.instructions);
    if(check(work.year))
        $("#year").val(work.year);
    if(check(work.techDetails))
        $("#techDetails").val(work.techDetails);
    if(check(work.requirements))
        $("#requirements").val(work.requirements.join(","));
    if(check(work.editorialStatement))
        $("#editorialStatement").val(work.editorialStatement);
    if(check(work.video))
        $("#video").val(work.video);
    if(check(work.externalUrl))
        $("#externalURL").val(work.externalUrl);
    if(check(work.copyright))
        $("#copyright").val(work.copyright);
    if(check(work.previousPublication))
        $("#previousPublication").val(work.previousPublication.join(","));
    if(check(work.references))
        $("#references").val(work.references.join(","));
}

function renderInformation(data){

    putObjectIntoCache("works",data);
    let worksSel = $("#worksDropDown");
    $.each(data, function (i, work) {
        worksSel.append($('<option>', {
            value: work.id,
            text: work.title
        }));
    });
}

function renderTagsDropDown(data){
    let tagsSel=$("#tags");

    putObjectIntoCache("tags",data);
    $.each(data,function (i, tag){
        tagsSel.append($('<option>',{
            value:tag.name.toLowerCase(),
            text: tag.name.toLowerCase()
        }));
    });
    tagsSel.multiSelect('refresh');
}

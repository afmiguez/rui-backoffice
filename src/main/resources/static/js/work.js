class Work{

    url
    title
    authorData=[]
    node
    banner
    icon
    location ={}
    tags = []
    language = []
    beginLinks = []
    instructions
    year
    techDetails
    requirements = []
    artist = {}
    editorialStatement
    sourceFiles
    video
    externalUrl
    copyright
    previousPublication = []
    references = []

    constructor(fields,tags) {
        this.tags=tags;
        this.populateFromFields(fields);
    }

    populateFromFields(fields){
        let author={};
        for(let i=0;i<$(fields).length;i++){
            let field=$(fields)[i];
            let id=$(field).attr('id');
            let value=$(field).val();

            if(id==='authorFirst'){
                author.first=value;
            }else if(id==='authorLast'){
                author.last=value;
                this.authorData.push(author);
                author={};
            }
            else if(id==='requirements'){
                this.requirements=value.split(',');
            }
            else if(id==='previousPublication'){
                this.previousPublication=value.split(',');
            }
            else if(id==='references'){
                this.references=value.split(',');
            }
            else if(id==='beginLinks'){
                let beginLink={}
                beginLink.displayText='Begin';
                beginLink.link=value;
                this.beginLinks.push(beginLink);
            }else if(id==='city'){
                this.location.city=value;
            }else if(id==='country'){
                this.location.country=value;
            }else if(id==='language'){
                this.language=value.split(',');
            }
            else{
                this[id]=value;
            }
        }
    }
}
$(document).ready(function(){
    $.ajax({
            url: "SpcController",
            data:{action:"load"},
            method:"POST",
            success: function(data){
                listerSpc(data);
            },
            error: function(data){
                console.log(data);
            }
        });
    $("#submitspc").click(function(){
        //alert("kamal");
        if(parseInt($("#id").val()) ===0)
        {
        var code = $("#code").val();
        var libelle = $("#libelle").val();
        if(code !== "" && libelle !== " "){
       // console.log(code +" "+libelle)
        $.ajax({
            url: "SpcController",
            data:{action:"add",code: code, libelle: libelle},
            method:"POST",
            success: function(data){
                listerSpc(data);
            },
            error: function(data){
                console.log(data);
            }
        });
        $("#code").val("");
        $("#libelle").val("");
    }
   }
 });
    $("#submitspcupdate").click(function(){
         if(parseInt($("#id").val()) !==0)
         {
        var code = $("#code").val();
        var libelle = $("#libelle").val();
        var id = $("#id").val();
        if(code !== " " && libelle !==" "){
       // console.log(code +" "+libelle)
        $.ajax({
            url: "SpcController",
            data:{action:"edit",id: id ,code: code, libelle: libelle},
            method:"POST",
            success: function(data){
                listerSpc(data);
            },
            error: function(data){
                console.log(data);
            }
        
        });
        $("#code").val("");
        $("#libelle").val("");
    }
   }
  });
    $("#content").on("click",'.badge',function(){
        //alert($(this).attr("data-role"));
        if($(this).attr("data-role")==="delete"){
        $.ajax({
            url:'SpcController',
            data:{action:"delete",id :$(this).attr("indice")},
            method:"POST",
            success: function(data){
                listerSpc(data);
            },
            error: function(data){
                console.log(data);
            }
        });
    }else{
        //alert($(this).attr("value1"));
           $.ajax({
            url:'SpcController',
            data:{action:"update",id :$(this).attr("indice")},
            method:"GET",
            success: function(data){
               $("#code").val(data.code);
               $("#libelle").val(data.libelle);
               $("#id").val(data.id);
            },
            error: function(data){
                console.log(data);
            }
        });
    }
    });
    function listerSpc(data){
        var ligne=" ";
        var option="";
        data.forEach(function(e){
            ligne +="<tr><th scope='row'>"+e.id+"</th><td value='"+e.code+"' >"+e.code+"</td><td valuel="+e.libelle+">"+e.libelle+"</td><td><button indice="+e.id+" data-role='delete' class='badge badge-secondary'>Delete</button>&nbsp;&nbsp;&nbsp;<button indice="+e.id+" data-role='update' class='badge badge-warning'>Update</button></td></tr>";
            option +="<option value="+e.id+" >"+e.libelle+"</option>";
        });
        $("#content").html(ligne);
        $("#inputSpc").html(option);
    }
});

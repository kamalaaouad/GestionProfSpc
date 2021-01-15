$(document).ready(function () {
    //alert("test");
    $.ajax({
        url: "ProfesseurController",
        data: {action: "charge"},
        method: "POST",
        success: function (data) {
            //console.log(data);
            listerProf(data);
        },
        error: function () {
            //console.log(error);
        }
    });
    $("#search").click(function () {
        // console.log("helloe");
        var id = $("#inputSpc").val();
        console.log(id);
        $.ajax({
            url: "ProfesseurController",
            data: {action: "search", id: id},
            method: "POST",
            success: function (data) {
                //console.log(data);
                listerProf(data);
            },
            error: function () {
                console.log(error);
            }
        });
    });
    $("#searchdate").click(function () {
        console.log("helloe");
        var date1 = $("#date1").val();
        var date2 = $("#date2").val();
        console.log(date1 + "  " + date2);
        $.ajax({
            url: "ProfesseurController",
            data: {action: "searchdate", date1: date1, date2: date2},
            method: "POST",
            success: function (data) {
                console.log("rah dkhel")
                console.log(data);
                searchProf(data);
            },
            error: function () {
                console.log("error");
            }
        });
    });
    $("#submitt").click(function () {
        if (parseInt($("#inputId").val()) === 0) {
            var nom = $("#inputNom").val();
            var prenom = $("#inputPrenom").val();
            var tele = $("#inputTele").val();
            var email = $("#inputEmail").val();
            var date = $("#inputDate").val();
            var sexe = $("#inputSexe").val();
            var spc = $("#inputSpc").val();
            console.log("ajout" + nom);
            if ($("#inputNom").val() !== " " && $("#inputPrenom").val() !== " " && $("#inputTele").val() !== " " && $("#inputEmail").val() !== " " && $("#inputDate").val() !== " " && $("#inputSexe").val() !== " " && $("#inputSpc").val() !== " ")
            {

                $.ajax({
                    url: "ProfesseurController",
                    data: {action: "add", nom: nom, prenom: prenom, tele: tele, email: email, date: date, sexe: sexe, spc: spc},
                    method: "POST",
                    success: function (data) {
                        console.log("data ja o mjabch");
                        listerProf(data);
                    },
                    error: function (data) {
                        console.log(data);
                    }
                });
                $("#inputNom").val("");
                $("#inputPrenom").val("");
                $("#inputTele").val("");
                $("#inputEmail").val("");
                $("#inputDate").val("");
                $("#inputSexe").val("");
                $("#inputSpc").val("");
                $("#inputId").val("");
            }
        }
    });
    $("#submitupdate").click(function () {
        if (parseInt($("#inputId").val()) !== 0) {
            var nom = $("#inputNom").val();
            var prenom = $("#inputPrenom").val();
            var tele = $("#inputTele").val();
            var email = $("#inputEmail").val();
            var date = $("#inputDate").val();
            var sexe = $("#inputSexe").val();
            var spc = $("#inputSpc").val();
            var id = $("#inputId").val();
            console.log("update" + id);
            if ($("#inputNom").val() !== " " && $("#inputPrenom").val() !== " " && $("#inputTele").val() !== " " && $("#inputEmail").val() !== " " && $("#inputDate").val() !== " " && $("#inputSexe").val() !== " " && $("#inputSpc").val() !== " ")
            {
                $.ajax({
                    url: "ProfesseurController",
                    data: {action: "update", id: id, nom: nom, prenom: prenom, tele: tele, email: email, date: date, sexe: sexe, spc: spc},
                    method: "POST",
                    success: function (data) {
                        console.log("her hna")
                        console.log(data);
                        listerProf(data);
                    },
                    error: function () {
                        //console.log(data);
                    }
                });
                $("#inputNom").val("");
                $("#inputPrenom").val("");
                $("#inputTele").val("");
                $("#inputEmail").val("");
                $("#inputDate").val("");
                $("#inputSexe").val("");
                $("#inputSpc").val("");
                $("#inputId").val("");
            }
        }
    });

    $("#contentprof").on('click', '.badge', function () {
        // alert($(this).attr("indice"));
        if ($(this).attr("role") === "delete") {
            $.ajax({
                url: "ProfesseurController",
                data: {action: "delete", id: $(this).attr("indice")},
                method: "POST",
                success: function (data) {
                    console.log(data);
                    listerProf(data);
                },
                error: function () {
                    console.log(error);
                }
            });
        } else {
            $.ajax({
                url: "ProfesseurController",
                data: {action: "edit", id: $(this).attr("indice")},
                method: "POST",
                success: function (data) {
                    var dateEmp = new Date(data.dateemp);

                    $("#inputId").val(data.id);
                    $("#inputNom").val(data.nom);
                    $("#inputPrenom").val(data.prenom);
                    $("#inputTele").val(data.tele);
                    $("#inputEmail").val(data.email);
                    var now = new Date();

                    var day = ("0" + dateEmp.getDate()).slice(-2);
                    var month = ("0" + (dateEmp.getMonth() + 1)).slice(-2);
                    var dateEmpFormatted = dateEmp.getFullYear() + "-" + (month) + "-" + (day);
                    console.log(dateEmpFormatted);
                    $("#inputDate").val(dateEmpFormatted);
                    $("#inputSexe").val(data.sexe);
                    $("#inputSpc").val(data.id_spc);
                    // console.log(data);
                },
                error: function () {
                    console.log(error);
                }
            });
        }
    });
    function listerProf(data) {
        var ligne = " ";

        // console.log(data);

        data.forEach(function (e) {
            ligne += "<tr><th scope='row'>" + e.id + "</th><td>"
                    + e.nom + "</td><td>" + e.prenom + "</td><td>"
                    + e.tele + "</td><td>" + e.email + "</td><td>"
                    + e.dateemp + "</td><td>" + e.sexe + "</td><td>"
                    + e.spc.libelle + "</td><td><button role='delete' indice="
                    + e.id + " class='badge badge-secondary'>Delete</button>&nbsp;&nbsp;&nbsp;<button indice="
                    + e.id + " class='badge badge-warning'>Update</button></td> </tr>";

        });
        $("#contentprof").html(ligne);

    }
    function searchProf(data) {
        var ligne = " ";

        // console.log(data);

        data.forEach(function (e) {
            ligne += "<tr><th scope='row'>" + e.id + "</th><td>"
                    + e.nom + "</td><td>" + e.prenom + "</td><td>"
                    + e.tele + "</td><td>" + e.email + "</td><td>"
                    + e.dateemp + "</td><td>" + e.sexe + "</td><td>"
                    + e.spc.libelle + "</td> </tr>";

        });
        $("#contentprof").html(ligne);

    }
});


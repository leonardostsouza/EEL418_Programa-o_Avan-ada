/* 
 * Este codigo faz parte do primeiro trabalho desenvolvido para o curso
 * EEL 418 - Programacao Avanacada, ministrado no primeiro semestre
 * de 2016 pelo professor Jorge Lopes de Souza Leao
 * Autor: Leonardo dos Santos Teixeira de Souza
 * DRE: 112086681
 */
var resultG;
var resultGNo = 0;

function checkboxBehavior() {
    if (document.getElementById('idcheckpatrimonio').checked) {
        // patrimonio
        document.getElementById('idpatrimonio2').style.color = "black";
        //titulo
        document.getElementById('idTituloSelect').value = "-";
        document.getElementById('idtitulo2').style.color = "lightgray";
        // autoria
        document.getElementById('idAutoriaSelect').value = "-";
        document.getElementById('idautoria2').style.color = "lightgray";
        // veiculo
        document.getElementById('idVeiculoSelect').value = "-";
        document.getElementById('idveiculo2').style.color = "lightgray";
        // date
        document.getElementById('iddatapublicacao21').style.color = "lightgray";
        document.getElementById('iddatapublicacao22').style.color = "lightgray";
        // palchave
        document.getElementById('idpalchave2').style.color = "lightgray";
    } else {
        // patrimonio
        document.getElementById('idpatrimonio2').style.color = "lightgray";
        //titulo
        document.getElementById('idtitulo2').style.color = "black";
        // autoria
        document.getElementById('idautoria2').style.color = "black";
        // veiculo
        document.getElementById('idveiculo2').style.color = "black";
        // date
        document.getElementById('iddatapublicacao21').style.color = "black";
        document.getElementById('iddatapublicacao22').style.color = "black";
        // palchave
        document.getElementById('idpalchave2').style.color = "black";
    }
}

function menuBehavior(option) {
    cleanTextFields('all');
    allowEdit(false);
    //showSearchResult(false);
    document.getElementById('idDivResultados').style.display = 'none';
    switch (option) {
        case "entrar":
            // menu options handling
            document.getElementById('menuEntrar').style.display = 'none';
            document.getElementById('menuSair').style.display = 'block';
            document.getElementById('menuCatalogacao').style.display = 'block';
            document.getElementById('menuBusca').style.display = 'none';

            // page content handling
            document.getElementById('idDivLogin').style.display = 'none';
            document.getElementById('idDivBusca').style.display = 'table';
            document.getElementById('idDivCatalogacao').style.display = 'none';

            break;
        case "catalogacao":
            allowEdit(true);
            // menu options handling
            document.getElementById('menuCatalogacao').style.display = 'none';
            document.getElementById('menuBusca').style.display = 'block';

            // page content handling
            document.getElementById('idDivLogin').style.display = 'none';
            document.getElementById('idDivBusca').style.display = 'none';
            document.getElementById('idDivCatalogacao').style.display = 'table';
            break;

        case "sair":
            // menu options handling
            document.getElementById('menuEntrar').style.display = 'block';
            document.getElementById('menuSair').style.display = 'none';
            document.getElementById('menuCatalogacao').style.display = 'none';
            document.getElementById('menuBusca').style.display = 'none';

            // page content handling
            document.getElementById('idDivLogin').style.display = 'table';
            document.getElementById('idDivBusca').style.display = 'none';
            document.getElementById('idDivCatalogacao').style.display = 'none';
            break;

        case "busca":
            // menu options handling
            document.getElementById('menuCatalogacao').style.display = 'block';
            document.getElementById('menuBusca').style.display = 'none';

            // page content handling
            document.getElementById('idDivLogin').style.display = 'none';
            document.getElementById('idDivBusca').style.display = 'table';
            document.getElementById('idDivCatalogacao').style.display = 'none';
            // patrimonio
            document.getElementById('idpatrimonio2').style.color = "lightgray";
            //titulo
            document.getElementById('idtitulo2').style.color = "black";
            // autoria
            document.getElementById('idautoria2').style.color = "black";
            // veiculo
            document.getElementById('idveiculo2').style.color = "black";
            // date
            document.getElementById('iddatapublicacao21').style.color = "black";
            document.getElementById('iddatapublicacao22').style.color = "black";
            // palchave
            document.getElementById('idpalchave2').style.color = "black";
            break;

        default:
            alert("ERRO");
            break;
    }
}

function cleanTextFields(option) {
    switch (option) {
        case 'all':
            cleanTextFields('login');
            cleanTextFields('busca');
            cleanTextFields('catalogacao');
            break;

        case 'login':
            document.getElementById('idUsuario').value = '';
            document.getElementById('idSenha').value = '';
            break;

        case 'busca':
            //patrionio
            document.getElementById('idpatrimonio2').value = '';
            document.getElementById('idcheckpatrimonio').checked = false;
            document.getElementById('idpatrimonio2').style.color = "lightgray";

            // titulo
            document.getElementById('idtitulo2').value = '';
            document.getElementById('idTituloSelect').value = "-";
            document.getElementById('idtitulo2').style.color = "black";

            // autoria
            document.getElementById('idautoria2').value = '';
            document.getElementById('idAutoriaSelect').value = "-";
            document.getElementById('idautoria2').style.color = "black";

            // veiculo
            document.getElementById('idveiculo2').value = '';
            document.getElementById('idVeiculoSelect').value = "-";
            document.getElementById('idveiculo2').style.color = "black";

            // data da publicacao
            document.getElementById('iddatapublicacao21').value = '';
            document.getElementById('iddatapublicacao22').value = '';
            document.getElementById('iddatapublicacao21').style.color = "black";
            document.getElementById('iddatapublicacao22').style.color = "black";

            // Palavras Chave
            document.getElementById('idpalchave2').value = '';
            document.getElementById('idpalchave2').style.color = "black";
            break;

        case 'catalogacao':
            document.getElementById('idpatrimonio3').value = '';
            document.getElementById('idtitulo3').value = '';
            document.getElementById('idautoria3').value = '';
            document.getElementById('idveiculo3').value = '';
            document.getElementById('iddatapublicacao3').value = '';
            document.getElementById('idpalchave3').value = '';
            document.getElementById('idnomearquivo3').value = '';
            break;

        default:
            alert('Erro de parametro em cleanTextFields');
    }
}

function allowEdit(option) {
    document.getElementById('idtitulo3').readOnly = !option;
    document.getElementById('idautoria3').readOnly = !option;
    document.getElementById('idveiculo3').readOnly = !option;
    document.getElementById('iddatapublicacao3').readOnly = !option;
    document.getElementById('idpalchave3').readOnly = !option;
    document.getElementById('idnomearquivo3').readOnly = !option;
    
    var bgColor = "lightgray";
    if (option){
        bgColor = "#ffffff";
    }
    document.getElementById('idpatrimonio3').style.backgroundColor = bgColor;
    document.getElementById('idtitulo3').style.backgroundColor = bgColor;
    document.getElementById('idautoria3').style.backgroundColor = bgColor;
    document.getElementById('idveiculo3').style.backgroundColor = bgColor;
    document.getElementById('iddatapublicacao3').style.backgroundColor = bgColor;
    document.getElementById('idpalchave3').style.backgroundColor = bgColor;
    document.getElementById('idnomearquivo3').style.backgroundColor = bgColor;
    
}

function searchToEdit(resultNo) {
    resultGNo = resultNo;
    menuBehavior('catalogacao');
    document.getElementById('idpatrimonio3').value = resultG.data[resultNo].patrimonio;
    document.getElementById('idtitulo3').value = resultG.data[resultNo].titulo;
    document.getElementById('idautoria3').value = resultG.data[resultNo].autoria;
    document.getElementById('idveiculo3').value = resultG.data[resultNo].veiculo;
    document.getElementById('iddatapublicacao3').value = resultG.data[resultNo].dataPublicacao.split(" ")[0];
    document.getElementById('idpalchave3').value = resultG.data[resultNo].palchave;
    document.getElementById('idnomearquivo3').value = resultG.data[resultNo].nomeArquivo;
    allowEdit(false);
}

function showSearchResult(result) {
    if (result.size > 0) {
        document.getElementById('idDivResultados').style.display = 'table';
        var resultHTML = "<table class='center' border='0'><tr><th>"
                + "Patrimonio</th><th>Titulo</th><th>Autoria</th><th>"
                + "&nbsp;&nbsp;&nbsp;</th></tr>";
        var itt = 0;
        for (itt = 0; itt < result.size; itt++) {
            resultHTML += "<tr><td>" + result.data[itt].patrimonio
                    + "</td>\n\<td class=\"align-left\">"
                    + result.data[itt].titulo + "</td>\n\<td class=\"align-left\">"
                    + result.data[itt].autoria + "</td><td><div onClick=\"searchToEdit("
                    + itt + ")\">&nbsp;&nbsp;&nbsp; "
                    + "<img src=\"images/edit_pencil.png\" alt=\"edit\"></div></td></tr>";
        }
        resultHTML += "</table>";
        document.getElementById('idTabelaResultados').innerHTML = resultHTML;
    } else {
        alert("Nenhum resultado encontrado");
    }
    var printResultadosHTML = "A busca retornou <b>" + result.size + "</b> ";
    if (result.size == 1) {
        printResultadosHTML += "resultado";
    } else {
        printResultadosHTML += "resultados";
    }
    document.getElementById('idNroRows').innerHTML = printResultadosHTML;
}

function sendRequest(operation) {
    // Preparacao do pedido
    var requestJSON = {
        "op": operation,
        "patrimonio": "",
        "patrimonioOpt": (document.getElementById('idcheckpatrimonio').checked) ? "true" : "false",
        "titulo": "",
        "tituloOpt": document.getElementById('idTituloSelect')[document.getElementById("idTituloSelect").selectedIndex].value,
        "autoria": "",
        "autoriaOpt": document.getElementById('idAutoriaSelect')[document.getElementById("idAutoriaSelect").selectedIndex].value,
        "veiculo": "",
        "veiculoOpt": document.getElementById('idVeiculoSelect')[document.getElementById("idVeiculoSelect").selectedIndex].value,
        "dataPublicacao": "",
        "dataIni": "",
        "dataFim": "",
        "nomeArquivo": "",
        "palchave": ""
    };

    switch (operation) {
        case "search":
            if ((document.getElementById('idpatrimonio2').value === "" &&
                    document.getElementById('idtitulo2').value === "" &&
                    document.getElementById('idautoria2').value === "" &&
                    document.getElementById('idveiculo2').value === "" &&
                    document.getElementById('iddatapublicacao21').value === "" &&
                    document.getElementById('iddatapublicacao22').value === "" &&
                    document.getElementById('idpalchave2').value === "") ||
                    ((!document.getElementById('idcheckpatrimonio').checked) &&
                            document.getElementById('idTituloSelect').value === "-" &&
                            document.getElementById('idAutoriaSelect').value === "-" &&
                            document.getElementById('idVeiculoSelect').value === "-" &&
                            document.getElementById('iddatapublicacao21').value === "" &&
                            document.getElementById('iddatapublicacao22').value === "" &&
                            document.getElementById('idpalchave2').value === "")) {
                alert("Adicione, pelo menos, um parametro para a busca");
            } else {
                // text fields
                requestJSON.patrimonio = document.getElementById('idpatrimonio2').value;
                requestJSON.titulo = document.getElementById('idtitulo2').value;
                requestJSON.autoria = document.getElementById('idautoria2').value;
                requestJSON.veiculo = document.getElementById('idveiculo2').value;
                requestJSON.dataIni = document.getElementById('iddatapublicacao21').value + " 00:00:00.0";
                requestJSON.dataFim = document.getElementById('iddatapublicacao22').value + " 00:00:00.0";
                requestJSON.palchave = document.getElementById('idpalchave2').value;
                makeRequest(requestJSON, "search");
            }
            break;
        case "delete":
            requestJSON.patrimonio = document.getElementById('idpatrimonio3').value;
            makeRequest(requestJSON, "delete");
            allowEdit(false);
            break;
        case "create":
        case "update":
            requestJSON.patrimonio = document.getElementById('idpatrimonio3').value;
            requestJSON.titulo = document.getElementById('idtitulo3').value;
            requestJSON.autoria = document.getElementById('idautoria3').value;
            requestJSON.veiculo = document.getElementById('idveiculo3').value;
            requestJSON.dataPublicacao = document.getElementById('iddatapublicacao3').value + " 00:00:00.0";
            requestJSON.nomeArquivo = document.getElementById('idnomearquivo3').value;
            requestJSON.palchave = document.getElementById('idpalchave3').value;
            makeRequest(requestJSON, "update");
            allowEdit(false);
            break;
        default:
            alert("ERRO - Operação inválida");
            break;
    }
}

function downloadBook() {
    var file = "livros/";
    if (resultG.data[resultGNo].nomeArquivo != "") {
        file += resultG.data[resultGNo].nomeArquivo;
        window.open(file);
    } else {
        alert("Este arquivo não está disponível para download");
    }

}

function makeRequest(requestJSON, op) {
    document.getElementById('idLoading').style.display = 'block';
    var stringJSON = JSON.stringify(requestJSON);
    var objPedidoAJAX = new XMLHttpRequest();
    objPedidoAJAX.open("POST", "controller");
    objPedidoAJAX.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    // Prepara recebimento da resposta: tipo da resposta JSON!
    objPedidoAJAX.responseType = 'json';
    objPedidoAJAX.onreadystatechange =
            function () {
                if (objPedidoAJAX.readyState === 4) {
                    document.getElementById('idLoading').style.display = 'none';
                    if (objPedidoAJAX.status === 200) {
                        // A resposta 'response' já é avaliada para json!
                        // Ao contraro da resposta responseText.
                        resultG = objPedidoAJAX.response;
                        if (op === "search") {
                            showSearchResult(resultG);
                        } else {
                            if (resultG.isSuccessful == true) {
                                alert("Operação realizada com sucesso");
                                menuBehavior('busca');
                            } else {
                                alert("Erro ao realizar operação");
                            }
                        }
                    }
                }
            };
    // Envio do pedido
    objPedidoAJAX.send(stringJSON);
}
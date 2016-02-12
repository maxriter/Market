$(document).ready(function () {

    updateStatistics();

    $("#clear-good-table").click(function () {
        clearTable();
        displayResponseSuccessMessage("Table clear");
    });

    $("#update-statistics").click(function () {
        updateStatistics();
        displayResponseSuccessMessage("Market Statistics updated");
    });

    $("#about").click(function () {
        $("#about-modal").modal("show");
    });

    $(document).on('click', '.btn-delete-good', function () {
        id = $(this).closest('tr').attr('id');
        $("#delete-good-modal").val(id).modal("show");
    });

    $("#add-good").click(function () {
        var name = $.trim($('#create-good-name').val());
        var price = $.trim($('#create-good-price').val());
        if (name.length == 0) {
            displayResponseWarningMessage("Specify name of good");
            return;
        }
        if (price.length != 0) {
            if (!isPriceCorrect(price)) {
                displayResponseWarningMessage("Price isn't correct");
                return;
            }
        }
        var json = '{"name": "' + name + '", "price": "' + price + '"}';
        $.ajax({
            type: "POST",
            url: '/good',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.status === "Error") {
                    displayResponseErrorMessage(data.message);
                    return;
                }
                updateStatistics();
                displayResponseSuccessMessage("Good added");
            }
        });
    });

    $("#delete").click(function () {
        var id = $('#delete-good-modal').val();
        $.ajax({
                type: "DELETE",
                url: '/good',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: id,
                success: function () {
                    getAllGoods();
                    updateStatistics();
                    displayResponseSuccessMessage("Good deleted");
                }
            }
        );
        $('#delete-good-modal').modal('toggle');
    });

    $("#search").click(function () {
        var id = $.trim($('#id').val());
        var name = $.trim($('#name').val());
        var priceFrom = $.trim($('#price-from').val());
        var priceTo = $.trim($('#price-to').val());
        if (!(isPriceCorrect(priceFrom)) || !(isPriceCorrect(priceTo))) {
            displayResponseWarningMessage("Price isn't correct");
            return;
        }
        var json = '{"id": "' + id + '", "name": "' + name + '", "priceFrom": "' + priceFrom + '", "priceTo": "' + priceTo + '"}';
        $.ajax({
            type: "POST",
            url: '/filter',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.status === "Error") {
                    displayResponseErrorMessage(data.message);
                    return;
                }
                outputGoods(data);
                displayResponseSuccessMessage("Result is shown in table");
            }
        });
    });

    $(document).on('dblclick', '.good-row', function () {
        var id = $(this).closest('tr').attr('id');
        $("#update-good-modal").val(id).modal("show");
    });

    $("#update").click(function () {
        var id = parseInt($('#update-good-modal').val());
        var name = $.trim($('#newName').val());
        var price = $.trim($('#newPrice').val());
        if ((price.length == 0) || (name.length == 0) || (!(isPriceCorrect(price)))) {
            displayResponseWarningMessage("Not all required fields are filled correctly");
            $('#update-good-modal').modal('toggle');
            return;
        }
        var json = '{"id": "' + id + '", "name": "' + name + '", "price": "' + price + '"}';
        $.ajax({
                type: "PUT",
                url: '/good',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: json,
                success: function (data) {
                    if (data.status === "Error") {
                        displayResponseErrorMessage(data.message);
                        return;
                    }
                    updateStatistics();
                    getAllGoods();
                    displayResponseSuccessMessage("Good updated");
                }
            }
        );
        $('#update-good-modal').modal('toggle');
    });

    function clearResponseMessage() {
        $("#response-message-span").removeClass("label-default label-primary label-success label-info label-warning label-danger").empty();
        $("#response-message").empty();
    }

    function displayResponseErrorMessage(data) {
        clearResponseMessage();
        $('#response-message-span').addClass("label-danger").text("Error");
        $("#response-message").text(data);
    }

    function displayResponseSuccessMessage(data) {
        clearResponseMessage();
        $('#response-message-span').addClass("label-success").text("Success");
        $("#response-message").text(data);
    }

    function displayResponseWarningMessage(data) {
        clearResponseMessage();
        $('#response-message-span').addClass("label-warning").text("Warning");
        $("#response-message").text(data);
    }

    function outputGoods(data) {
        clearTable();
        $.each(data, function (index, value) {
            var button = $("<button class='btn btn btn-default btn-delete-good'><span class='glyphicon glyphicon-trash'></span>Delete</button>");
            button.appendTo($("<tr id=" + value.id + "><td>" + value.id + "</td><td>" + value.name + "</td><td>" + value.price + "</td></tr>").addClass("good-row").insertAfter($("#all-good-table-first-row")));
        });
    }

    function clearTable() {
        $('#good-table tr:not(:first)').remove();
    }

    function isPriceCorrect(val) {
        if (val.length == 0)return true;
        if (val === "0") return false;
        return ((val % val) == 0 );
    }

    function getAllGoods() {
        $.get("/allGoods", function (data) {
            outputGoods(data)
        });
    }

    function updateStatistics() {
        $.get("/statistics", function (data) {
            $("#good-count").text(data.goodCount);
            $("#max-price").text(data.maxPrice);
            $("#min-price").text(data.minPrice);
            $("#avg-price").text(data.avgPrice);
        });
    }

});


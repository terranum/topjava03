<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<dandelion:bundle includes="topjavaDatatable"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="meals.title"/></h3>

            <c:set var="ajaxUrl" value="ajax/profile/meals/"/>
            <div class="view-box">
                <form:form modelAttribute="filter" class="form-horizontal" action="ajax/profile/meals/filter" charset="utf-8"
                           accept-charset="UTF-8" id="filter">
                    <div class="form-group">
                        <spring:bind path="startDate">
                            <label class="col-sm-2">From Date</label>
                            <div class="col-sm-2"><form:input path="startDate" class="form-control date-picker" placeholder="Start Date"/></div>
                        </spring:bind>
                        <spring:bind path="endDate">
                            <label class="col-sm-2">To Date</label>
                            <div class="col-sm-2"><form:input path="endDate" class="form-control date-picker" placeholder="End Date"/></div>
                        </spring:bind>
                    </div>
                    <div class="form-group">
                        <spring:bind path="startTime">
                            <label class="col-sm-2">From Time</label>
                            <div class="col-sm-2"><form:input path="startTime" class="form-control time-picker" placeholder="Start Time"/></div>
                        </spring:bind>
                        <spring:bind path="endTime">
                            <label class="col-sm-2">To Time</label>
                            <div class="col-sm-2"><form:input path="endTime" class="form-control time-picker" placeholder="End Time"/></div>
                        </spring:bind>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button type="submit" class="btn btn-primary pull-right">Filter</button>
                        </div>
                    </div>
                </form:form>

                <a class="btn btn-sm btn-info" id="add">Add Meal</a>
                <datatables:table id="datatable" url="${ajaxUrl}" row="user" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">
                    <datatables:column title="Date" filterable="false" sortInitDirection="desc" property="dateTime"/>
                    <datatables:column title="Description" property="description"/>
                    <datatables:column title="Calories" filterable="false" property="calories"/>
                    <datatables:column sortable="false" renderFunction="renderUpdateBtn"/>
                    <datatables:column sortable="false" renderFunction="renderDeleteBtn"/>

                    <datatables:callback type="init" function="makeEditable"/>
                    <datatables:callback type="createdrow" function="updateCreatedRow"/>
                </datatables:table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Meal Details:</h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime" class="form-control datetime-picker" id="dateTime"
                                   name="dateTime" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Calories</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories"
                                   placeholder="2000">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var ajaxUrl = '${ajaxUrl}';

    function init() {
        $('#filter').submit(function () {
            updateTable();
            return false;
        });

        $('.date-picker').datetimepicker({
            timepicker: false,
            format: 'Y-m-d',
            lang:'ru'
        });
        $('.time-picker').datetimepicker({
            datepicker: false,
            format: 'H:i',
            lang:'ru'
        });

        $('#dateTime').datetimepicker({
            format: 'Y-m-d H:i',
            lang:'ru'
        });
    }

    function updateTable() {
        var frm = $('#filter');
        $.ajax({
            type: "POST",
            url: frm.attr('action'),
            data: frm.serialize(),
            success: function(data){
                updateTableWithData(data);
            }
        });
    }

    function updateCreatedRow(row, data, dataIndex) {
        $(row).css("color", data.exceed ? 'red' : 'green');
    }

/*
    function refresh() {
        $.each($('td.exceed'), function (key, item) {
            var cell = $(item);
            cell.parent().css("color", cell.html() == 'true' ? 'red' : 'green');
        });
    }
*/
</script>
</html>
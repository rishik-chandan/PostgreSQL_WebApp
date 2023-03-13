<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <style>
      html {
        font-size: 14px;
        font-family: Arial, Helvetica, sans-serif;
      }

      #navbar {
        background-color: #333;
        overflow: hidden;
      }

      #navbar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
      }

      #navbar li {
        float: left;
      }

      #navbar li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
      }

      #navbar li a:hover {
        background-color: #111;
      }
    </style>

    <title>WebApp by Rishik Chandan</title>

    <link rel="stylesheet" href="styles/kendo.dimetis.css" />
    <link rel="stylesheet" href="styles/kendo.default-main.css" />
    <link rel="stylesheet" href="styles/kendo.common.css" />

    <script src="js/jquery.js"></script>
    <script src="js/kendo.all.js"></script>
  </head>
  <body text="white" style="background-color: #545454">
    <div class="container-fluid p-0 m-0"><%@include file="navbar.jsp"%></div>
    <br />
    <div class="demo-section" style="margin: auto; text-align: center">
      <label for="mode-switch" class="k-switch-label">Employee</label>
      <input
        type="checkbox"
        id="mode-switch"
        class="k-switch"
        aria-label="Mode Switch"
        checked="unchecked"
      />
      Department
    </div>

    <div
      id="heading"
      style="
        font-size: 36px;
        text-align: center;
        color: white;
        margin-top: 20px;
        text-shadow: 2px 2px 4px #000000;
      "
    ></div>

    <div id="employee-container" class="container">
      <br />
      <div
        id="empGrid"
        style="width: 70%; margin: 0 auto; text-align: center"
      ></div>
      <br />
      <form
        action="downloadEmpExcel"
        method="get"
        style="margin: 0 auto; text-align: center"
      >
        <button
          type="submit"
          id="download-btn"
          class="k-button k-button-solid-primary k-button-solid k-button-md k-rounded-md"
        >
          Download Employees as Excel
        </button>
      </form>
    </div>

    <div id="department-container" class="container">
      <br />
      <div
        id="deptGrid"
        style="width: 70%; margin: 0 auto; text-align: center"
      ></div>
      <br />
      <form
        action="downloadDeptExcel"
        method="get"
        style="margin: 0 auto; text-align: center"
      >
        <button
          type="submit"
          id="download-btn"
          class="k-button k-button-solid-primary k-button-solid k-button-md k-rounded-md"
        >
          Download Department as Excel
        </button>
      </form>
    </div>

    <script>
      $(document).ready(function () {
        $("#navbar").kendoMenu();

        $("#mode-switch").kendoSwitch({
          checked: false,
          messages: {
            checked: "",
            unchecked: "",
          },
        });

        $("#heading").text("PostgreSQL entries :");

        $("#skeleton").kendoSkeletonContainer({
          animation: "pulse",
        });

        var empGrid = $("#empGrid").kendoGrid({
          dataSource: {
            transport: {
              read: {
                url: "OperationServlet?operation=getAllEmp&module=employee",
                type: "POST",
              },
            },
          },
          height: 550,
          groupable: true,
          loaderType: "skeleton",
          sortable: true,
          pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 3,
          },
          parameterMap: function (options, operation) {
            if (operation === "read") {
              return {
                module: module,
                operation: operation,
              };
            } else {
              return options;
            }
          },
          columns: [
            {
              field: "emp_id",
              title: "ID",
            },
            {
              field: "emp_name",
              title: "Name",
            },
            {
              field: "emp_address",
              title: "Address",
            },
            {
              field: "emp_email",
              title: "Email",
            },
            {
              field: "emp_phoneNo",
              title: "Phone Number",
            },
            {
              field: "emp_state",
              title: "State",
            },
            {
              field: "emp_place",
              title: "Place",
            },
            {
              field: "emp_dept_id",
              title: "Department ID",
            },
            {
              command: [
                {
                  text: "Edit",
                  click: editItem,
                },
                {
                  text: "Delete",
                  click: deleteItem,
                },
              ],
              title: "Actions",
              width: "200px",
            },
          ],
          editable: "popup",
        });

        var empDataSource = $("#empGrid").data("kendoGrid").dataSource;

        function editItem(e) {
          e.preventDefault();
          var dataItem = this.dataItem($(e.currentTarget).closest("tr"));

          // Open the built-in popup editor
          var grid = $("#empGrid").data("kendoGrid");
          grid.editRow($(e.currentTarget).closest("tr"));

          // Attach a click event handler to the "Update" button of the popup editor
          $(".k-grid-update", grid.editable.element).on("click", function (e) {
            // Get the updated data from the popup editor
            var updatedData = grid.editable.dataSource.data()[0];

            // Perform an AJAX POST request to the editEmp servlet
            $.ajax({
              url: "editEmp",
              type: "POST",
              data: JSON.stringify(updatedData),
              contentType: "application/json; charset=utf-8",
              success: function (response) {
                console.log("Editing Item : " + dataItem.emp_id);
                empDataSource.read();
              },
              error: function (xhr, status, error) {
                console.log(error);
                empDataSource.read();
              },
            });
          });
        }

        function deleteItem(e) {
          e.preventDefault();
          var dataItem = this.dataItem($(e.currentTarget).closest("tr"));

          if (window.confirm("Are you sure you want to delete this item?")) {
            $.ajax({
              url: "deleteEmp",
              type: "POST",
              data: {
                emp_id: dataItem.emp_id,
              },
              success: function (response) {
                console.log("Deleting Item : " + dataItem.emp_id),
                  empDataSource.read();
              },
              error: function (xhr, status, error) {
                console.log(error);
                empDataSource.read();
              },
            });
          }
        }

        var deptDataSource = new kendo.data.DataSource({
          pageSize: 10,
          transport: {
            read: {
              url: "viewDept",
            },
            update: {
              url: "updateDept",
            },
          },
        });

        $("#deptGrid").kendoGrid({
          dataSource: deptDataSource,
          height: 550,
          toolbar: ["create"],
          groupable: true,
          loaderType: "skeleton",
          sortable: true,
          pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 3,
          },
          columns: [
            {
              field: "id",
              title: "Dept. ID",
            },
            {
              field: "name",
              title: "Dept. Name",
            },
            {
              field: "description",
              title: "Dept. Description",
            },
            {
              command: ["edit", "destroy"],
              title: "&nbsp;",
              width: "250px",
            },
          ],
          editable: "popup",
        });

        // Get the switch and form elements
        const switchEl = $("#mode-switch").data("kendoSwitch");
        const empGridElement = $("#employee-container");
        const deptGridElement = $("#department-container");

        // Hide the Kendo UI Grid control
        deptGridElement.hide();

        // Listen for changes to the switch
        switchEl.bind("change", function (e) {
          if (e.checked) {
            // Department mode is active
            empGridElement.hide();
            deptGridElement.show();
          } else {
            // Employee mode is active
            empGridElement.show();
            deptGridElement.hide();
          }
        });
      });

      /* $.ajax({
		  url: "/viewEmp",
		  dataType: "json",
		  success: function (e) {
		    options.success(e);
		  },
		}); */
    </script>
  </body>
</html>

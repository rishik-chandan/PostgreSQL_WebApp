<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
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

    <title>Upload Form to Database</title>
    <link rel="stylesheet" href="styles/kendo.default-main.css" />
    <link rel="stylesheet" href="styles/kendo.dimetis.css" />
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

    <br />

    <!-- Generate the KendoUI form using the script tag -->
    <form
      id="employee-form"
      method="post"
      action="empFormSubmit"
      class="k-form k-content"
      style="width: 40%; margin: 0 auto; text-align: left"
    ></form>

    <form
      id="department-form"
      method="post"
      action="deptFormSubmit"
      class="k-form k-content"
      style="width: 40%; margin: 0 auto; text-align: left"
    ></form>

    <script>
      $("#navbar").kendoMenu();

      $("#mode-switch").kendoSwitch({
        checked: false,
        messages: {
          checked: "",
          unchecked: "",
        },
      });

      $("#employee-form").kendoForm({
        orientation: "vertical",
        items: [
          {
            type: "group",
            label: "Employee insertion Form",
            items: [
              {
                field: "ID",
                label: "ID:",
                validation: {
                  required: true,
                  pattern: {
                    value: "[0-9]+",
                    message: "Only numbers are allowed",
                  },
                },
                attributes: {
                  type: "text",
                  name: "ID",
                  placeholder: "Enter your ID",
                  pattern: "[0-9]*",
                },
              },
              {
                field: "Name",
                label: "Name:",
                validation: {
                  required: true,
                },
                attributes: {
                  type: "text",
                  name: "Name",
                  placeholder: "Enter your name",
                  pattern: "[A-Za-z]*",
                },
                pattern: {
                  value: "[A-Za-z]+",
                  message: "Only alphabets are allowed",
                },
              },
              {
                field: "Address",
                label: "Address:",
                validation: {
                  required: true,
                },
                attributes: {
                  type: "text",
                  name: "Address",
                  placeholder: "Enter your Address",
                },
              },
              {
                field: "Email",
                label: "Email:",
                validation: {
                  required: true,
                  email: true,
                },
                attributes: {
                  type: "text",
                  name: "Email",
                  placeholder: "Enter your Email Address",
                },
              },
              {
                field: "PhoneNo",
                label: "Phone No:",
                validation: {
                  required: true,
                },
                attributes: {
                  type: "text",
                  name: "PhoneNo",
                  maxlength: "10",
                  placeholder: "Enter your 10-Digit Phone number",
                  pattern: "[0-9]*",
                },
                pattern: {
                  value: "^\\d{10}$",
                  message: "Enter a valid phone number",
                },
              },

              {
                field: "State",
                label: "State:",
                validation: {
                  required: true,
                  pattern: {
                    value: "[A-Za-z ]+",
                    message: "Only alphabets and spaces are allowed",
                  },
                },
                attributes: {
                  type: "text",
                  name: "State",
                  placeholder: "Enter your State",
                  pattern: "[A-Za-z]*",
                },
              },
              {
                field: "Place",
                label: "Place:",
                validation: {
                  required: true,
                  pattern: {
                    value: "[A-Za-z ]+",
                    message: "Only alphabets and spaces are allowed",
                  },
                },
                attributes: {
                  type: "text",
                  name: "Place",
                  placeholder: "Enter your Place",
                  pattern: "[A-Za-z]*",
                },
              },
              {
                field: "dept_id",
                label: "Department ID:",
                validation: {
                  required: true,
                  pattern: {
                    value: "[0-9]+",
                    message: "Only numbers are allowed",
                  },
                },
                attributes: {
                  type: "text",
                  name: "dept_id",
                  placeholder: "Enter the Department ID",
                  pattern: "[0-9]*",
                },
              },
            ],
          },
        ],
        validation: {
          messages: {
            required: "This field is required",
          },
        },
      });

      const formElement = $("#department-form").kendoForm({
        orientation: "vertical",
        items: [
          {
            type: "group",
            label: "Department insertion Form",
            items: [
              {
                field: "dept_ID",
                label: "Department ID:",
                validation: {
                  required: true,
                  pattern: {
                    value: "[0-9]+",
                    message: "Only numbers are allowed",
                  },
                },
                attributes: {
                  type: "text",
                  name: "dept_ID",
                  placeholder: "Enter department ID",
                  pattern: "[0-9]*",
                },
              },

              {
                field: "dept_Name",
                label: "Name:",
                validation: {
                  required: true,
                },
                attributes: {
                  type: "text",
                  name: "dept_Name",
                  placeholder: "Enter a department name",
                  pattern: "[A-Za-z ]*",
                },
                pattern: {
                  value: "[A-Za-z ]+",
                  message: "Only alphabets are allowed",
                },
              },
              {
                field: "dept_Description",
                label: "Description:",
                validation: {
                  required: true,
                },
                attributes: {
                  type: "text",
                  name: "dept_Description",
                  placeholder: "Enter a Description",
                },
              },
            ],
          },
        ],
        validation: {
          messages: {
            required: "This field is required",
          },
        },
      });

      // Hide the Kendo UI Form control
      formElement.hide();

      // Get the switch and form elements
      const switchEl = $("#mode-switch").data("kendoSwitch");
      const employeeForm = $("#employee-form");
      const departmentForm = $("#department-form");

      // Listen for changes to the switch
      switchEl.bind("change", function (e) {
        if (e.checked) {
          // Department mode is active
          employeeForm.hide();
          departmentForm.show();
        } else {
          // Employee mode is active
          employeeForm.show();
          departmentForm.hide();
        }
      });
    </script>
  </body>
</html>

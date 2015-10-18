//helper functions
function htmlEncode(value) {
  return $('<div/>').text(value).html();
}

function renderTemplate(template, model, el) {
  $.get(template, function (data) {
    var rendered = Mustache.render(data, model);
    el.html(rendered);
  });
}

$.fn.serializeObject = function () {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function () {
    if (o[this.name] !== undefined) {
      if (!o[this.name].push) {
        o[this.name] = [o[this.name]];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
};

//Model
var Employees = Backbone.Collection.extend({
  url: '/employee'
});

var Employee = Backbone.Model.extend({
  urlRoot: '/employee'
});

//List View
var EmployeeListView = Backbone.View.extend({
  el: '.page',
  render: function () {
    var that = this;
    var employees = new Employees();
    employees.fetch({
      success: function (employees) {
        renderTemplate('list.mustache', {employees: employees.toJSON()}, that.$el);
      }
    })
  }
});

var employeeListView = new EmployeeListView();

//Edit View
var EmployeeEditView = Backbone.View.extend({
  el: '.page',
  events: {
    'submit .edit-employee-form': 'saveEmployee',
    'click .delete': 'deleteEmployee'
  },
  saveEmployee: function (ev) {
    var employeeDetails = $(ev.currentTarget).serializeObject();
    var employee = new Employee();
    employee.save(employeeDetails, {
      dataType: 'text',
      success: function (employee) {
        router.navigate('', {trigger: true});
      },
      error: function () {
        console.log('error while saving');
      }
    });
    return false;
  },
  deleteEmployee: function (ev) {
    var empId = this.employee.get("employeeId");
    //set id as employee id to make successful delete call with correct url
    this.employee.set("id", empId);
    this.employee.destroy({
      dataType: 'text',
      success: function () {
        console.log('destroyed');
        router.navigate('', {trigger: true});
      },
      error: function () {
        console.log('error while deleting');
      }
    });
    return false;
  },
  render: function (options) {
    var that = this;
    if (options.id) {
      that.employee = new Employee({id: options.id});
      that.employee.fetch({
        success: function (employee) {
          renderTemplate('edit.mustache', {employee: employee.toJSON()}, that.$el);
        }
      })
    } else {
      renderTemplate('create.mustache', {}, that.$el);
    }
  }
});

var employeeEditView = new EmployeeEditView();

//Router
var Router = Backbone.Router.extend({
  routes: {
    "": "home",
    "edit/:id": "edit",
    "new": "edit"
  }
});
var router = new Router;
router.on('route:home', function () {
  // render employee list
  employeeListView.render();
})
router.on('route:edit', function (id) {
  employeeEditView.render({id: id});
})

Backbone.history.start();

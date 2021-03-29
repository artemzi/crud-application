import {getFormEdit, getFormView} from './selectors';
import { editFormSuccess, editFormPending, setNewEditableForm} from './actions';
import axios from 'axios';


export function setupForm() {
  return function _resetForm(dispatch, getState) {
    const form = getFormView(getState());
    dispatch(setNewEditableForm(form));
  }
}


export function saveForm() {
  return function _saveForm(dispatch, getState) {
    dispatch(editFormPending());
    const form = getFormEdit(getState());

    //todo тут должна быть нормальная валидация поля, пока явно ставим дефолт
    const salary = +form.field || 100500;

    axios.post(`http://localhost:8132/api/employee/create`, { name: form.title, salary })
        .then(res => {
          form.title = res.data.name
          form.field = +res.data.salary
        })

    dispatch(editFormSuccess(form));
  }
}

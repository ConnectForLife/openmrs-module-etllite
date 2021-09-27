
import { ToastStatusContent } from './toast-builder-util';
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import '../../css/toast.scss';

export const successToast = message => displayToast(message, toast.TYPE.SUCCESS);

export const errorToast = message => displayToast(message, toast.TYPE.ERROR);

export const displayToast = (message, type) =>
    toast(
        <ToastStatusContent {... { message, type }} />,
        {
            autoClose: true,
            closeButton: false,
            className: 'toast-item',
            hideProgressBar: true
        });

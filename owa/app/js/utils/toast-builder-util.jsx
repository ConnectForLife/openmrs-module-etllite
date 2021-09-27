// Component used for building toasts (notification) with OpenMRS style guideline matching

export const ToastStatusContent = ({ message, type }) => {
  const customClass = "toast-item-image toast-item-image-" + type;
  return (
    <div className="toast-item-wrapper etl-toast-item-wrapper">
      <div className={customClass}></div>
      <p>{message}</p>
    </div>
  );
}

export const CloseButton = ({ closeToast = function(){} }) => (
  <div className="toast-item-close" onClick={closeToast} />
);

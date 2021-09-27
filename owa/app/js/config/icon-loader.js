import { 
  faClipboardList,
  faHome,
  faChevronRight
} from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';


export const loadIcons = () => {
  library.add(
    faClipboardList,
    faHome,
    faChevronRight
  );
};

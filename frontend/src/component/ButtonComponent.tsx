import {Link, type LinkProps} from "react-router-dom";
import styles from '../style/Button.module.css'

interface ButtonProps {
    to: LinkProps['to'],
    title: string,
    onClick?: React.MouseEventHandler<HTMLAnchorElement>
}

export default function Button({to, title, onClick }: ButtonProps) {
    return (
        <span className={styles.link} onClick={onClick}>
            <Link className={styles.linkText} to={to}>
                {title}
            </Link>
        </span>
    )
}
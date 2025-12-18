import {Link, type LinkProps} from "react-router-dom";
import styles from '../style/Button.module.css'

interface ButtonLinkProps {
    to: LinkProps['to'],
    title: string,
    onClick?: React.MouseEventHandler<HTMLAnchorElement>
}

export default function ButtonLink({to, title, onClick }: ButtonLinkProps) {
    return (
        <span className={styles.link} onClick={onClick}>
            <Link className={styles.linkText} to={to}>
                {title}
            </Link>
        </span>
    )
}